package br.com.senior.service.processor;

import br.com.domain.intelbras.services.IntelbrasService;
import br.com.domain.senior.abstration.HeaderMessage;
import br.com.domain.senior.abstration.ResponseProcessor;
import br.com.domain.senior.model.ManagerDevice;
import br.com.domain.senior.request.CardListStatusCommand;
import br.com.senior.client.ClientCSMCommunication;
import br.com.senior.service.command.InteractionCommands;

public final class CardListStatusProcessor extends ResponseProcessor<CardListStatusCommand> {
    public CardListStatusProcessor(ClientCSMCommunication driver, IntelbrasService intelbrasApiService, InteractionCommands interactionCommands) {
        super(driver, intelbrasApiService, interactionCommands);
    }
    
    @Override
    
    public void process(CardListStatusCommand message) throws Exception {
        if (message == null) {
            throw new NullPointerException("A mensagem não pode ser nula.");
        } else {
            ManagerDevice device = CONTROLLER.findDevicesByIdOrIp(message.getDeviceId());
            if (device != null) {
                int recordCount;
                switch (message.getCardListType()) {
                    case 0:
                        recordCount = device.getCardAllowedListSize();
                        break;
                        
                    case 1:
                        recordCount = device.getCardBlockedListSize();
                        break;
                        
                    default:
                        throw new Exception(String.format("Tipo inválido de lista de cartão: %d.", message.getCardListType()));
                }
                
                HeaderMessage response = MSG_FACTORY.buildListStatusResponse(message.getMessageNumber(), message.getDeviceId(), recordCount, 0, 0,
                                                                             device.getDate());
                this.driver.sendMessage(response);
            } else {
                throw new Exception(String.format("Dispositivo não cadastrado: %d.", message.getDeviceId()));
            }
        }
    }
    
}
