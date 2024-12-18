package br.com.senior.service.message;

import java.util.HashMap;
import java.util.Map;

import br.com.domain.intelbras.services.IntelbrasService;
import br.com.domain.senior.abstration.HeaderMessage;
import br.com.domain.senior.abstration.ResponseProcessor;
import br.com.domain.senior.request.AccessValidationTimeoutCommand;
import br.com.domain.senior.request.ActivateEmergencyCommand;
import br.com.domain.senior.request.AddSetBiometricData;
import br.com.domain.senior.request.AddSetDeviceCardListRep;
import br.com.domain.senior.request.BiometricDataUpdated;
import br.com.domain.senior.request.CardListStatusCommand;
import br.com.domain.senior.request.CollectBiometriesCommand;
import br.com.domain.senior.request.DateTimeCommand;
import br.com.domain.senior.request.DeactivateEmergencyCommand;
import br.com.domain.senior.request.DeleteCardListCommand;
import br.com.domain.senior.request.DeletePersonBiometry;
import br.com.domain.senior.request.IsAliveCommand;
import br.com.domain.senior.request.PersonUpdateRep;
import br.com.domain.senior.request.RequestDeviceStatusCommand;
import br.com.domain.senior.request.SetCardIdentifierCommand;
import br.com.domain.senior.request.SetCardListCommand;
import br.com.domain.senior.request.UnlockDeviceCommand;
import br.com.domain.senior.request.UpdateFirmwareCommand;
import br.com.domain.senior.response.BiometricDataResponse;
import br.com.domain.senior.response.DateTimeResponse;
import br.com.domain.senior.response.LockDeviceCommand;
import br.com.domain.senior.response.NACK;
import br.com.domain.senior.response.UpdateDeleteBiometricTechnology;
import br.com.domain.senior.response.UpdateDeleteCorporation;
import br.com.domain.senior.response.UpdateDeleteDevice;
import br.com.senior.client.ClientCSMCommunication;
import br.com.senior.service.command.InteractionCommands;
import br.com.senior.service.processor.AccessValidationTimeoutProcessor;
import br.com.senior.service.processor.ActivateEmergencyProcessor;
import br.com.senior.service.processor.AddSetBiometricDataProcessor;
import br.com.senior.service.processor.AddSetDeviceCardListRepProcessor;
import br.com.senior.service.processor.BiometricDataResponseProcessor;
import br.com.senior.service.processor.BiometricDataUpdatedProcessor;
import br.com.senior.service.processor.CardListStatusProcessor;
import br.com.senior.service.processor.CollectBiometriesProcessor;
import br.com.senior.service.processor.DateTimeProcessor;
import br.com.senior.service.processor.DateTimeResponseProcessor;
import br.com.senior.service.processor.DeactivateEmergencyProcessor;
import br.com.senior.service.processor.DeleteCardListProcessor;
import br.com.senior.service.processor.DeletePersonBiometryProcessor;
import br.com.senior.service.processor.IsAliveProcessor;
import br.com.senior.service.processor.LockDeviceProcessor;
import br.com.senior.service.processor.NACKProcessor;
import br.com.senior.service.processor.PersonUpdateRepProcessor;
import br.com.senior.service.processor.RequestDeviceStatusProcessor;
import br.com.senior.service.processor.SetCardIdentifierProcessor;
import br.com.senior.service.processor.SetCardListProcessor;
import br.com.senior.service.processor.UnlockDeviceProcessor;
import br.com.senior.service.processor.UpdateDeleteBiometricTechnologyResponseProcessor;
import br.com.senior.service.processor.UpdateDeleteCorporationResponseProcessor;
import br.com.senior.service.processor.UpdateDeleteDeviceResponseProcessor;
import br.com.senior.service.processor.UpdateFirmwareProcessor;

public final class ResponseProcessorFactory {
	private final Map<Class<? extends HeaderMessage>, ResponseProcessor<? extends HeaderMessage>> processors = new HashMap<>();

	public ResponseProcessorFactory(ClientCSMCommunication driver, IntelbrasService intelbrasApiService,
			InteractionCommands interactionCommands) {
		// Implemented
		this.processors.put(NACK.class, new NACKProcessor(driver, intelbrasApiService, interactionCommands));
		// Implemented
		this.processors.put(ActivateEmergencyCommand.class,
				new ActivateEmergencyProcessor(driver, intelbrasApiService, interactionCommands));
		// Implemented
		this.processors.put(DeactivateEmergencyCommand.class,
				new DeactivateEmergencyProcessor(driver, intelbrasApiService, interactionCommands));
		// Implemented
		this.processors.put(LockDeviceCommand.class,
				new LockDeviceProcessor(driver, intelbrasApiService, interactionCommands));
		// Implemented
		this.processors.put(UnlockDeviceCommand.class,
				new UnlockDeviceProcessor(driver, intelbrasApiService, interactionCommands));
		// Implemented
		this.processors.put(IsAliveCommand.class,
				new IsAliveProcessor(driver, intelbrasApiService, interactionCommands));
		// Implemented
		this.processors.put(DateTimeCommand.class,
				new DateTimeProcessor(driver, intelbrasApiService, interactionCommands));
		// Implemented
		this.processors.put(DateTimeResponse.class,
				new DateTimeResponseProcessor(driver, intelbrasApiService, interactionCommands));
		// Implemented
		this.processors.put(SetCardListCommand.class,
				new SetCardListProcessor(driver, intelbrasApiService, interactionCommands));

		this.processors.put(RequestDeviceStatusCommand.class,
				new RequestDeviceStatusProcessor(driver, intelbrasApiService, interactionCommands));
		this.processors.put(DeleteCardListCommand.class,
				new DeleteCardListProcessor(driver, intelbrasApiService, interactionCommands));
		this.processors.put(CardListStatusCommand.class,
				new CardListStatusProcessor(driver, intelbrasApiService, interactionCommands));
		this.processors.put(BiometricDataUpdated.class,
				new BiometricDataUpdatedProcessor(driver, intelbrasApiService, interactionCommands));
		this.processors.put(UpdateDeleteDevice.class,
				new UpdateDeleteDeviceResponseProcessor(driver, intelbrasApiService, interactionCommands));
		this.processors.put(UpdateDeleteCorporation.class,
				new UpdateDeleteCorporationResponseProcessor(driver, intelbrasApiService, interactionCommands));
		this.processors.put(UpdateDeleteBiometricTechnology.class,
				new UpdateDeleteBiometricTechnologyResponseProcessor(driver, intelbrasApiService, interactionCommands));
		this.processors.put(PersonUpdateRep.class,
				new PersonUpdateRepProcessor(driver, intelbrasApiService, interactionCommands));
		this.processors.put(AddSetDeviceCardListRep.class,
				new AddSetDeviceCardListRepProcessor(driver, intelbrasApiService, interactionCommands));
		this.processors.put(DeletePersonBiometry.class,
				new DeletePersonBiometryProcessor(driver, intelbrasApiService, interactionCommands));
		this.processors.put(AddSetBiometricData.class,
				new AddSetBiometricDataProcessor(driver, intelbrasApiService, interactionCommands));
		this.processors.put(BiometricDataResponse.class,
				new BiometricDataResponseProcessor(driver, intelbrasApiService, interactionCommands));
		this.processors.put(CollectBiometriesCommand.class,
				new CollectBiometriesProcessor(driver, intelbrasApiService, interactionCommands));
		this.processors.put(UpdateFirmwareCommand.class,
				new UpdateFirmwareProcessor(driver, intelbrasApiService, interactionCommands));
		this.processors.put(AccessValidationTimeoutCommand.class,
				new AccessValidationTimeoutProcessor(driver, intelbrasApiService, interactionCommands));
		this.processors.put(SetCardIdentifierCommand.class,
				new SetCardIdentifierProcessor(driver, intelbrasApiService, interactionCommands));
	}

	@SuppressWarnings("unchecked")
	public <M extends HeaderMessage> ResponseProcessor<M> getResponse(M message) {
		return (ResponseProcessor<M>) this.processors.get(message.getClass());
	}

}
