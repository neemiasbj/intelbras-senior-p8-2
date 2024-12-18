package br.com.senior.service.processor;

import java.io.IOException;

import br.com.domain.intelbras.services.IntelbrasService;
import br.com.domain.senior.abstration.HeaderMessage;
import br.com.domain.senior.abstration.ResponseProcessor;
import br.com.domain.senior.model.ManagerDevice;
import br.com.domain.senior.request.DateTimeCommand;
import br.com.senior.client.ClientCSMCommunication;
import br.com.senior.service.command.InteractionCommands;

public final class DateTimeProcessor extends ResponseProcessor<DateTimeCommand> {
    public DateTimeProcessor(ClientCSMCommunication driver, IntelbrasService intelbrasApiService, InteractionCommands interactionCommands) {
        super(driver, intelbrasApiService, interactionCommands);
    }
    
    public void process(DateTimeCommand message) throws InterruptedException, IOException {
        if (message == null) {
            throw new NullPointerException("A mensagem não pode ser nula.");
        } else {
            HeaderMessage response;
            ManagerDevice device = CONTROLLER.findDevicesByIdOrIp(message.getDeviceId());
            device.setDateTimeStatus(true);
            
            response = MSG_FACTORY.buildACK(message.getMessageNumber());
            this.driver.sendMessage(response);
            interactionCommands.sendRequestDateTime();
        }
    }
    
}
