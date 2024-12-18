package br.com.senior.service.processor;

import br.com.domain.intelbras.services.IntelbrasService;
import br.com.domain.senior.abstration.HeaderMessage;
import br.com.domain.senior.abstration.ResponseProcessor;
import br.com.domain.senior.request.DeletePersonBiometry;
import br.com.senior.client.ClientCSMCommunication;
import br.com.senior.service.command.InteractionCommands;

public class DeletePersonBiometryProcessor extends ResponseProcessor<DeletePersonBiometry> {
    public DeletePersonBiometryProcessor(ClientCSMCommunication driver, IntelbrasService intelbrasApiService, InteractionCommands interactionCommands) {
        super(driver, intelbrasApiService, interactionCommands);
    }
    
    public void process(DeletePersonBiometry message) throws Exception {
        if (message == null) {
            throw new NullPointerException("A mensagem não pode ser nula.");
        } else {
            HeaderMessage response = MSG_FACTORY.buildACK(message.getMessageNumber());
            this.driver.sendMessage(response);
        }
    }
    
}
