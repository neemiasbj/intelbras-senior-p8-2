package br.com.senior.service.processor;

import java.util.Collection;

import br.com.domain.intelbras.services.IntelbrasService;
import br.com.domain.senior.abstration.HeaderMessage;
import br.com.domain.senior.abstration.ResponseProcessor;
import br.com.domain.senior.model.CardUseIdentifier;
import br.com.domain.senior.request.SetCardIdentifierCommand;
import br.com.senior.client.ClientCSMCommunication;
import br.com.senior.service.command.InteractionCommands;

public class SetCardIdentifierProcessor extends ResponseProcessor<SetCardIdentifierCommand> {
    public SetCardIdentifierProcessor(ClientCSMCommunication driver, IntelbrasService intelbrasApiService, InteractionCommands interactionCommands) {
        super(driver, intelbrasApiService, interactionCommands);
    }
    
    public void process(SetCardIdentifierCommand message) throws Exception {
        if (message == null) {
            throw new NullPointerException("A mensagem não pode ser nula.");
        } else {
            // ManagerDevice device = CONTROLLER.findDevicesByIdOrIp(message.getDeviceId());
            Collection<CardUseIdentifier> cardsUseId = message.getCardUseIds();
            if (cardsUseId == null) {
                throw new NullPointerException("A lista de Identificadores de uso do cartao não pode ser nula");
            } else {
                HeaderMessage response = MSG_FACTORY.buildACK(message.getMessageNumber());
                this.driver.sendMessage(response);
            }
        }
    }
    
}
