package br.com.senior.service.processor;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.domain.intelbras.model.api.ResponseDeviceGenericModel;
import br.com.domain.intelbras.model.api.SetCardListModel;
import br.com.domain.intelbras.services.IntelbrasService;
import br.com.domain.senior.abstration.HeaderMessage;
import br.com.domain.senior.abstration.ResponseProcessor;
import br.com.domain.senior.model.CardReaderDevice;
import br.com.domain.senior.model.ManagerDevice;
import br.com.domain.senior.request.DeleteCardListCommand;
import br.com.senior.client.ClientCSMCommunication;
import br.com.senior.service.command.InteractionCommands;

public final class DeleteCardListProcessor extends ResponseProcessor<DeleteCardListCommand> {
    public DeleteCardListProcessor(ClientCSMCommunication driver, IntelbrasService intelbrasApiService, InteractionCommands interactionCommands) {
        super(driver, intelbrasApiService, interactionCommands);
    }
    
    public void process(DeleteCardListCommand message) throws Exception {
        if (message == null) {
            throw new NullPointerException("A mensagem não pode ser nula.");
        } else {
            HeaderMessage response;
            ManagerDevice device = CONTROLLER.findDevicesByIdOrIp(message.getDeviceId());
            Collection<CardReaderDevice> allCardReaderDevices = device.getAllCardReaderDevices();
            Iterator<CardReaderDevice> var4 = allCardReaderDevices.iterator();
            List<String> listCards = device.getCardAllowedList().stream().map(card -> String.valueOf(card.getId())).toList();
            
            while (var4.hasNext()) {
                CardReaderDevice cardReaderDevice = var4.next();
                cardReaderDevice.clearCardAllowedList();
                cardReaderDevice.clearCardBlockedList();
            }
            
            SetCardListModel cardListIntelbras = new SetCardListModel(device.getIp());
            cardListIntelbras.setCardsList(listCards);
            
            ResponseEntity<ResponseDeviceGenericModel> responseEntity = intelbrasApiService.addAllowedCardsList(cardListIntelbras);
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                ResponseDeviceGenericModel emergency = responseEntity.getBody();
                if (Boolean.TRUE.equals(emergency.getError()))
                    response = MSG_FACTORY.buildACK(message.getMessageNumber());
                else
                    response = MSG_FACTORY.buildNACK(message.getMessageNumber(), "Erro gerado no dispositivo ao processar a mensagem.", (byte) 3);
            } else {
                response = messageSendDeviceNack(message.getMessageNumber());
            }
            
            this.driver.sendMessage(response);
        }
    }
    
}
