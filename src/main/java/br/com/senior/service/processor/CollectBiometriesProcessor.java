package br.com.senior.service.processor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import br.com.domain.intelbras.services.IntelbrasService;
import br.com.domain.senior.abstration.HeaderMessage;
import br.com.domain.senior.abstration.ResponseProcessor;
import br.com.domain.senior.model.BiometricData;
import br.com.domain.senior.model.BiometricTechnology;
import br.com.domain.senior.model.ManagerDevice;
import br.com.domain.senior.model.Person;
import br.com.domain.senior.request.CollectBiometriesCommand;
import br.com.domain.senior.response.InsertUpdateBiometricDataWithPis;
import br.com.senior.client.ClientCSMCommunication;
import br.com.senior.service.command.InteractionCommands;

public final class CollectBiometriesProcessor extends ResponseProcessor<CollectBiometriesCommand> {
    public CollectBiometriesProcessor(ClientCSMCommunication driver, IntelbrasService intelbrasApiService, InteractionCommands interactionCommands) {
        super(driver, intelbrasApiService, interactionCommands);
    }
    
    @Override
    public void process(CollectBiometriesCommand message) throws Exception {
        if (message == null) {
            throw new NullPointerException("A mensagem não pode ser nula.");
        } else {
            ManagerDevice device = CONTROLLER.findDevicesByIdOrIp(message.getDeviceId());
            new ArrayList<>();
            Collection<Person> people = device.getPeople();
            Iterator<Person> var5 = people.iterator();
            
            while (true) {
                Person person;
                Collection<?> bioList;
                do {
                    if (!var5.hasNext()) {
                        HeaderMessage response = MSG_FACTORY.buildACK(message.getMessageNumber());
                        this.driver.sendMessage(response);
                        return;
                    }
                    
                    person = var5.next();
                    bioList = person.getAllBiometricData();
                } while (bioList.isEmpty());
                
                ArrayList<BiometricTechnology> bioTechList = new ArrayList<>();
                Iterator<?> var8 = bioList.iterator();
                
                while (var8.hasNext()) {
                    BiometricData bioData = (BiometricData) var8.next();
                    if (bioTechList.isEmpty()) {
                        bioTechList.add(bioData.getTechnology());
                    } else if (!bioTechList.contains(bioData.getTechnology())) {
                        bioTechList.add(bioData.getTechnology());
                    }
                }
                
                var8 = bioTechList.iterator();
                
                while (var8.hasNext()) {
                    BiometricTechnology bioTech = (BiometricTechnology) var8.next();
                    InsertUpdateBiometricDataWithPis insertUpdateBiometricDataWithPis = (InsertUpdateBiometricDataWithPis) MSG_FACTORY.buildInsertUpdateBiometricDataWithPis(person.getPis(),
                                                                                                                                                                             person.getBiometricData(bioTech));
                    this.driver.sendMessage(insertUpdateBiometricDataWithPis);
                }
            }
        }
    }
    
}
