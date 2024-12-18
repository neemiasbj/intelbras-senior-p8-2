package br.com.senior.service.processor;

import br.com.domain.intelbras.services.IntelbrasService;
import br.com.domain.senior.abstration.ResponseProcessor;
import br.com.domain.senior.response.UpdateDeleteDevice;
import br.com.senior.client.ClientCSMCommunication;
import br.com.senior.service.command.InteractionCommands;

public final class UpdateDeleteDeviceResponseProcessor extends ResponseProcessor<UpdateDeleteDevice> {
    public UpdateDeleteDeviceResponseProcessor(ClientCSMCommunication driver, IntelbrasService intelbrasApiService,
                                               InteractionCommands interactionCommands) {
        super(driver, intelbrasApiService, interactionCommands);
    }
    
    public void process(UpdateDeleteDevice message) throws Exception {
        if (message == null) {
            throw new NullPointerException("A mensagem não pode ser nula.");
        }
    }
    
}
