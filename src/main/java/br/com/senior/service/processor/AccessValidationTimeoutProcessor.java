package br.com.senior.service.processor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.com.domain.intelbras.services.IntelbrasService;
import br.com.domain.senior.abstration.HeaderMessage;
import br.com.domain.senior.abstration.ResponseProcessor;
import br.com.domain.senior.model.CardReaderDevice;
import br.com.domain.senior.model.enums.AccessType;
import br.com.domain.senior.model.enums.DeviceCommType;
import br.com.domain.senior.model.enums.DirectionsType;
import br.com.domain.senior.request.AccessValidationTimeoutCommand;
import br.com.senior.client.ClientCSMCommunication;
import br.com.senior.service.command.InteractionCommands;

public final class AccessValidationTimeoutProcessor extends ResponseProcessor<AccessValidationTimeoutCommand> {
	public AccessValidationTimeoutProcessor(ClientCSMCommunication driver, IntelbrasService intelbrasApiService,
			InteractionCommands interactionCommands) {
		super(driver, intelbrasApiService, interactionCommands);
	}

	@Override
	public void process(AccessValidationTimeoutCommand message) throws Exception {
		if (message == null) {
			throw new NullPointerException("A mensagem não pode ser nula.");
		} else {
			int cardReaderId = message.getCardReaderId();
			CardReaderDevice reader = CONTROLLER.getCardReaderById(cardReaderId);
			if (reader == null) {
				throw new Exception(String.format("Leitora não cadastrada (%d)", cardReaderId));
			} else {
				HeaderMessage response = MSG_FACTORY.buildACK(message.getMessageNumber());
				this.driver.sendMessage(response);
				List<Long> accompaniesWaiting = new ArrayList<>(reader.getAccompaniesWaiting());
				reader.clearAccompaniesWaiting();
				Iterator<Long> var6 = accompaniesWaiting.iterator();

				while (var6.hasNext()) {
					Long accompanyCard = var6.next();
					HeaderMessage notifyAccess = MSG_FACTORY.buildNotifyAccessEvent(cardReaderId, reader.getDate(),
							reader.getManager().getGmtMinutes(), (byte) 0, DirectionsType.ENTRANCE,
							DeviceCommType.ONLINE, accompanyCard, AccessType.ACCESS_DENIED_ACCOMPANY.getId(),
							(byte) reader.getPreviousLevel(), (byte) -1, 0, 0L, false, 0L, "", "");
					this.driver.sendMessage(notifyAccess);
				}

			}
		}
	}

}
