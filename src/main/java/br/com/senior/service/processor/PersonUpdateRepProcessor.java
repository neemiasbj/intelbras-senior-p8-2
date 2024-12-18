package br.com.senior.service.processor;

import br.com.domain.intelbras.services.IntelbrasService;
import br.com.domain.senior.abstration.HeaderMessage;
import br.com.domain.senior.abstration.ResponseProcessor;
import br.com.domain.senior.model.ManagerDevice;
import br.com.domain.senior.request.PersonUpdateRep;
import br.com.senior.client.ClientCSMCommunication;
import br.com.senior.service.command.InteractionCommands;

public class PersonUpdateRepProcessor extends ResponseProcessor<PersonUpdateRep> {
    public PersonUpdateRepProcessor(ClientCSMCommunication driver, IntelbrasService intelbrasApiService, InteractionCommands interactionCommands) {
        super(driver, intelbrasApiService, interactionCommands);
    }
    
    public void process(PersonUpdateRep message) throws Exception {
        if (message == null) {
            throw new NullPointerException("A mensagem não pode ser nula.");
        } else {
            ManagerDevice device = CONTROLLER.findDevicesByIdOrIp(message.getDeviceId());
            device.mergePerson(message.getPerson(), message.getBioOperation(), message.getOperation());
            HeaderMessage response = MSG_FACTORY.buildACK(message.getMessageNumber());
            this.driver.sendMessage(response);
        }
    }
    
}
