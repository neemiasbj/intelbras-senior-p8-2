package br.com.senior.service.processor;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.google.gson.Gson;

import br.com.domain.intelbras.model.api.ResponseDeviceGenericModel;
import br.com.domain.intelbras.model.api.SetCardListModel;
import br.com.domain.intelbras.services.IntelbrasService;
import br.com.domain.senior.abstration.HeaderMessage;
import br.com.domain.senior.abstration.ResponseProcessor;
import br.com.domain.senior.model.ManagerDevice;
import br.com.domain.senior.model.SimpleCard;
import br.com.domain.senior.request.SetCardListCommand;
import br.com.senior.client.ClientCSMCommunication;
import br.com.senior.service.command.InteractionCommands;

public final class SetCardListProcessor extends ResponseProcessor<SetCardListCommand> {
    public SetCardListProcessor(ClientCSMCommunication driver, IntelbrasService intelbrasApiService, InteractionCommands interactionCommands) {
        super(driver, intelbrasApiService, interactionCommands);
    }
    
    public void process(SetCardListCommand message) throws Exception {
        if (message == null) {
            throw new NullPointerException("A mensagem não pode ser nula.");
        } else {
            HeaderMessage response;
            ManagerDevice device = CONTROLLER.findDevicesByIdOrIp(message.getDeviceId());
            List<SimpleCard> cards = message.getCards();
            switch (message.getCardListType()) {
                case 0:
                    device.setCardAllowedList(cards);
                    break;
                    
                case 1:
                    device.setCardBlockedList(cards);
                    break;
                    
                default:
                    throw new Exception(String.format("Tipo inválido de lista de cartão: %d.", message.getCardListType()));
            }
            
            logger.info("message: {}", new Gson().toJson(message.getCards()));
            List<String> listCards = message.getCards().stream().map(card -> String.valueOf(card.getId())).toList();
            
            SetCardListModel cardListIntelbras = new SetCardListModel(device.getIp());
            cardListIntelbras.setCardsList(listCards);
            try {
                
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
            } catch (Exception e) {
                logger.error("Error: ", e);
                response = messageSendDeviceNack(message.getMessageNumber());
                this.driver.sendMessage(response);
            }
            
        }
    }
    
}
