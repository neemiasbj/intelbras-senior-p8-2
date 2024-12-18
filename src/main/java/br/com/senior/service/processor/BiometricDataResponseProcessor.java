package br.com.senior.service.processor;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import br.com.domain.intelbras.services.IntelbrasService;
import br.com.domain.senior.abstration.ResponseProcessor;
import br.com.domain.senior.model.BiometricData;
import br.com.domain.senior.model.ManagerDevice;
import br.com.domain.senior.model.Person;
import br.com.domain.senior.response.BiometricDataResponse;
import br.com.senior.client.ClientCSMCommunication;
import br.com.senior.service.command.InteractionCommands;

public class BiometricDataResponseProcessor extends ResponseProcessor<BiometricDataResponse> {
	public BiometricDataResponseProcessor(ClientCSMCommunication driver, IntelbrasService intelbrasApiService,
			InteractionCommands interactionCommands) {
		super(driver, intelbrasApiService, interactionCommands);
	}

	@Override
	public void process(BiometricDataResponse message) throws Exception {
		if (message == null) {
			throw new NullPointerException("A mensagem não pode ser nula.");
		} else {
			List<Person> people = message.getPersonList();
			Iterator<Person> peopleIterator = people.iterator();

			while (peopleIterator.hasNext()) {
				Person person = peopleIterator.next();
				Collection<ManagerDevice> devices = CONTROLLER.getAllDevices();
				Iterator<ManagerDevice> var6 = devices.iterator();

				Person personInDevice;

				ManagerDevice managerDevice = var6.next();
				personInDevice = managerDevice.getPerson(person.getIdPerson());

				personInDevice.clearBiometricData();
				Collection<BiometricData> biometries = person.getAllBiometricData();
				Iterator<BiometricData> personBiometries = biometries.iterator();

				while (personBiometries.hasNext()) {
					BiometricData biometricData = personBiometries.next();
					personInDevice.addBiometricData(biometricData);
				}
			}

		}
	}

}
