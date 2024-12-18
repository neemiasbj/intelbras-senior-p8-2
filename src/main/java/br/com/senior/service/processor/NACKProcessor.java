package br.com.senior.service.processor;

import java.io.IOException;

import br.com.domain.intelbras.services.IntelbrasService;
import br.com.domain.senior.abstration.ResponseProcessor;
import br.com.domain.senior.response.NACK;
import br.com.senior.client.ClientCSMCommunication;
import br.com.senior.service.command.InteractionCommands;

public class NACKProcessor extends ResponseProcessor<NACK> {
    public NACKProcessor(ClientCSMCommunication driver, IntelbrasService intelbrasApiService, InteractionCommands interactionCommands) {
        super(driver, intelbrasApiService, interactionCommands);
    }
    
    public void process(NACK message) throws IOException, InterruptedException {
        if (message == null) {
            throw new NullPointerException("A mensagem não pode ser nula.");
        } else {
            this.driver.sendMessage(message);
        }
    }
    
}
