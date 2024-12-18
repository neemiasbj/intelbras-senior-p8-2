package br.com.senior.service.processor;

import java.util.Iterator;

import br.com.domain.intelbras.services.IntelbrasService;
import br.com.domain.senior.abstration.HeaderMessage;
import br.com.domain.senior.abstration.ResponseProcessor;
import br.com.domain.senior.model.ManagerDevice;
import br.com.domain.senior.model.Person;
import br.com.domain.senior.request.AddSetDeviceCardListRep;
import br.com.senior.client.ClientCSMCommunication;
import br.com.senior.service.command.InteractionCommands;

public class AddSetDeviceCardListRepProcessor extends ResponseProcessor<AddSetDeviceCardListRep> {
    public AddSetDeviceCardListRepProcessor(ClientCSMCommunication driver, IntelbrasService intelbrasApiService, InteractionCommands interactionCommands) {
        super(driver, intelbrasApiService, interactionCommands);
    }
    
    @Override
    public void process(AddSetDeviceCardListRep message) throws Exception {
        if (message == null) {
            throw new NullPointerException("A mensagem não pode ser nula.");
        } else {
            ManagerDevice device = CONTROLLER.findDevicesByIdOrIp(message.getDeviceId());
            Iterator<?> var3;
            Person person;
            if (message.isOverwrite()) {
                var3 = device.getPeople().iterator();
                
                while (var3.hasNext()) {
                    person = (Person) var3.next();
                    device.deletePerson(person);
                }
                
                device.setPeople(message.getPeople());
            } else {
                var3 = message.getPeople().iterator();
                
                while (var3.hasNext()) {
                    person = (Person) var3.next();
                    device.mergePerson(person, (byte) 1, (byte) 0);
                }
            }
            
            HeaderMessage response = MSG_FACTORY.buildACK(message.getMessageNumber());
            this.driver.sendMessage(response);
        }
    }
    
}
