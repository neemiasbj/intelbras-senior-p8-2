package br.com.senior.service.processor;

import br.com.domain.intelbras.services.IntelbrasService;
import br.com.domain.senior.abstration.HeaderMessage;
import br.com.domain.senior.abstration.ResponseProcessor;
import br.com.domain.senior.request.UpdateFirmwareCommand;
import br.com.senior.client.ClientCSMCommunication;
import br.com.senior.service.command.InteractionCommands;

public class UpdateFirmwareProcessor extends ResponseProcessor<UpdateFirmwareCommand> {
    public UpdateFirmwareProcessor(ClientCSMCommunication driver, IntelbrasService intelbrasApiService, InteractionCommands interactionCommands) {
        super(driver, intelbrasApiService, interactionCommands);
    }
    
    public void process(UpdateFirmwareCommand message) throws Exception {
        if (message == null) {
            throw new NullPointerException("A mensagem não pode ser nula.");
        } else {
            // ManagerDevice device = CONTROLLER.findDevicesByIdOrIp(message.getDeviceId());
            HeaderMessage response = MSG_FACTORY.buildACK(message.getMessageNumber());
            this.driver.sendMessage(response);
        }
    }
    
}
