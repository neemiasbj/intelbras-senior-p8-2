package br.com.senior.service.processor;

import br.com.domain.intelbras.services.IntelbrasService;
import br.com.domain.senior.abstration.ResponseProcessor;
import br.com.domain.senior.model.BiometricData;
import br.com.domain.senior.model.Person;
import br.com.domain.senior.request.BiometricDataUpdated;
import br.com.senior.client.ClientCSMCommunication;
import br.com.senior.service.command.InteractionCommands;

public final class BiometricDataUpdatedProcessor extends ResponseProcessor<BiometricDataUpdated> {
    public BiometricDataUpdatedProcessor(ClientCSMCommunication driver, IntelbrasService intelbrasApiService, InteractionCommands interactionCommands) {
        super(driver, intelbrasApiService, interactionCommands);
    }
    
    public void process(BiometricDataUpdated message) {
        if (message == null) {
            throw new NullPointerException("A mensagem não pode ser nula.");
        } else {
            Person person = message.getPerson();
            BiometricData bioData = message.getBiometricData();
            BiometricData oldBioData = person.getBiometricData(bioData.getTechnology());
            if (oldBioData != null) {
                person.removeBiometricData(oldBioData);
            }
            
            if (!bioData.getTemplates().isEmpty()) {
                person.addBiometricData(bioData);
            }
            
        }
    }
    
}
