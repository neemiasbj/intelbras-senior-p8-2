package br.com.senior.service.message;

import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

import br.com.domain.senior.abstration.HeaderMessage;
import br.com.senior.usecase.AutenticationMiddlewareUseCase;
import br.com.senior.usecase.DecodeAccessEventsUseCase;
import br.com.senior.usecase.DecodeDeviceConfigUseCase;
import br.com.senior.usecase.DecodeDeviceDataResponseUseCase;
import br.com.senior.usecase.DecodeDeviceInteractionUseCase;
import br.com.senior.usecase.DecodeEventsAlarmsUsecase;
import br.com.senior.usecase.DecodeListDataUseCase;
import br.com.senior.usecase.DecodeResponseUseCase;

public final class HeaderMessageDecodeFactory {

	// private static final Logger logger =
	// LoggerFactory.getLogger(HeaderMessageDecodeFactory.class);

	public static HeaderMessage decode(ByteBuffer buffer) throws BufferUnderflowException {
		if (buffer == null) {
			throw new NullPointerException("Buffer não pode ser nulo. MessageDecodeFactory");
		} else {

			if (buffer.remaining() < 4) {
				throw new BufferUnderflowException();
			}

			byte messageNumber = buffer.get();
			byte messageType = buffer.get();
			byte messageId = buffer.get();

			// logger.info("Mensagem Recebida: NUMBER= {}, TYPE= {}, ID= {} ",
			// messageNumber, messageType, messageId);

			switch (messageType) {
			case 0:
				return decodeResponse(buffer, messageNumber, messageType, messageId);

			case 1:
				return decodeAuthentication(buffer, messageNumber, messageType, messageId);

			case 2:
				return decodeAccessValidation(buffer, messageNumber, messageType, messageId);

			case 3:
				return decodeNotifyDevice(buffer, messageNumber, messageType, messageId);

			case 4:
				return decodeDeviceConfig(buffer, messageNumber, messageType, messageId);

			case 5:
				return decodeListData(buffer, messageNumber, messageType, messageId);

			case 6:
				return decodeDeviceInteraction(buffer, messageNumber, messageType, messageId);

			default:
				return DecodeResponseUseCase.returnMessage(messageNumber);
			}
		}
	}

	private static HeaderMessage decodeResponse(ByteBuffer buffer, byte messageNumber, byte messageType,
			byte messageId) {
		switch (messageId) {
		case 0:
			return DecodeResponseUseCase.decodeAck(messageNumber);

		case 1:
			return DecodeResponseUseCase.decodeNack(messageNumber, buffer);

		case 2:
			return DecodeResponseUseCase.decodeUnsuportedProtocolVersionResponse(messageNumber);

		case 3:
			return DecodeResponseUseCase.decodeNotAuthenticatedResponse(messageNumber);

		case 4:
			return DecodeDeviceDataResponseUseCase.decodeDeviceDataResponse(messageNumber, messageType, messageId,
					buffer);

		case 5:
			return DecodeResponseUseCase.returnMessage(messageNumber);

		case 6:
			return DecodeResponseUseCase.decodeUndevelopedCommandResponse(messageNumber);

		default:
			return DecodeResponseUseCase.returnMessage(messageNumber);
		}
	}

	private static HeaderMessage decodeAuthentication(ByteBuffer buffer, byte messageNumber, byte messageType,
			byte messageId) {
		if (messageId == 1)
			return AutenticationMiddlewareUseCase.decodeAuthenticationSuccessfull(buffer, messageNumber);
		else
			return DecodeResponseUseCase.returnMessage(messageNumber);
	}

	private static HeaderMessage decodeAccessValidation(ByteBuffer buffer, byte messageNumber, byte messageType,
			byte messageId) {
		switch (messageId) {
		case 2:
			return DecodeAccessEventsUseCase.decodeAccessValidationResponse(buffer, messageNumber);

		case 3:
			return DecodeAccessEventsUseCase.decodeAccessValidationTimeout(buffer, messageNumber);

		default:
			return DecodeResponseUseCase.returnMessage(messageNumber);
		}
	}

	private static HeaderMessage decodeNotifyDevice(ByteBuffer buffer, byte messageNumber, byte messageType,
			byte messageId) {
		switch (messageId) {
		case 1:
			return DecodeEventsAlarmsUsecase.decodeNotifyDeviceAlarmed(buffer, messageNumber);

		case 4:
			return DecodeEventsAlarmsUsecase.decodeNotifyDeviceEvent(buffer, messageNumber);

		case 5:
			return DecodeEventsAlarmsUsecase.decodeNotifyDeviceResourceStatus(buffer, messageNumber);

		default:
			return DecodeResponseUseCase.returnMessage(messageNumber);
		}
	}

	private static HeaderMessage decodeDeviceConfig(ByteBuffer buffer, byte messageNumber, byte messageType,
			byte messageId) {
		switch (messageId) {
		case 1:
			return DecodeDeviceConfigUseCase.decodeDateTimeCommand(buffer, messageNumber);

		case 4:
			return DecodeDeviceConfigUseCase.decodeDateTimeResponse(buffer, messageNumber);

		case 6:
			return DecodeDeviceConfigUseCase.decodeIsAliveCommand(buffer, messageNumber);

		default:
			return DecodeResponseUseCase.returnMessage(messageNumber);
		}
	}

	private static HeaderMessage decodeListData(ByteBuffer buffer, byte messageNumber, byte messageType,
			byte messageId) {
		switch (messageId) {
		case 3:
			return DecodeListDataUseCase.decodeSetCardList(buffer, messageNumber);

		case 6:
			return DecodeListDataUseCase.decodeDeleteCardList(buffer, messageNumber);

		case 16:
			return DecodeListDataUseCase.decodeResponseBiometricData(buffer, messageNumber);

		case 31:
			return DecodeListDataUseCase.decodePersonUpdateRep(buffer, messageNumber);

		case 32:
			return DecodeListDataUseCase.decodeAddSetDeviceCardListRep(buffer, messageNumber);

		case 33:
			return DecodeDeviceDataResponseUseCase.decodeUpdateDeleteDevice(messageNumber, messageType, messageId,
					buffer);

		case 34:
			return DecodeListDataUseCase.decodeUpdateDeleteBiometricTechnology(buffer, messageNumber);

		case 36:
			return DecodeListDataUseCase.decodeAddSetBiometricData(buffer, messageNumber);

		case 37:
			return DecodeListDataUseCase.decodeDeletePersonBiometry(buffer, messageNumber);

		default:
			return DecodeResponseUseCase.returnMessage(messageNumber);
		}
	}

	private static HeaderMessage decodeDeviceInteraction(ByteBuffer buffer, byte messageNumber, byte messageType,
			byte messageId) {
		switch (messageId) {
		case 1:
			return DecodeDeviceInteractionUseCase.decodeRequestDeviceStatus(buffer, messageNumber);

		case 2:
			return DecodeDeviceInteractionUseCase.decodeLockDevice(buffer, messageNumber);

		case 3:
			return DecodeDeviceInteractionUseCase.decodeUnlockDevice(buffer, messageNumber);

		case 4:
			return DecodeDeviceInteractionUseCase.decodeActivateEmergency(buffer, messageNumber);

		case 5:
			return DecodeDeviceInteractionUseCase.decodeDeactivateEmergency(buffer, messageNumber);

		default:
			return DecodeResponseUseCase.returnMessage(messageNumber);
		}
	}

	// private static HeaderMessage decodeUpdateDeleteDeviceCorporation(ByteBuffer
	// buffer, byte messageNumber) {
	// UpdateDeleteCorporation response = new
	// UpdateDeleteCorporation(messageNumber);
	// buffer.getShort();
	// response.setOperation(buffer.get());
	// response.setCorpCNPJ(Utils.getDescription(buffer));
	// response.setCorpCPF(Utils.getDescription(buffer));
	// response.setCorpCEI(Utils.getDescription(buffer));
	// short descSize = buffer.getShort();
	// response.setCorpName(Utils.getString(buffer, descSize));
	// int deviceCount = buffer.getShort();
	// List<Integer> devicesIds = new ArrayList<>();
	//
	// for (int i = 0; i < deviceCount; ++i) {
	// devicesIds.add(buffer.getInt());
	// }
	//
	// return response;
	// }
	// private static HeaderMessage decodeCollectEvents(ByteBuffer buffer, byte
	// messageNumber) {
	// buffer.getShort();
	// int devID = buffer.getInt();
	// int startingDate = buffer.getInt();
	// int finalDate = buffer.getInt();
	// long startingNSR = buffer.getLong();
	// long finalNSR = buffer.getLong();
	// return startingDate == 0 && finalDate == 0 ? new
	// CollectEventsCommand(messageNumber, devID, startingDate, finalDate,
	// startingNSR, finalNSR) : new CollectEventsCommand(messageNumber, devID,
	// startingDate, finalDate, 0L, 0L);
	// }
	//
	// private static HeaderMessage decodeCollectBiometries(ByteBuffer buffer, byte
	// messageNumber) {
	// buffer.getShort();
	// int deviceId = buffer.getInt();
	// return new CollectBiometriesCommand(messageNumber, deviceId);
	// }
	//
	// private static HeaderMessage decodeUpdateFirmware(ByteBuffer buffer, byte
	// messageNumber) {
	// buffer.getShort();
	// int deviceId = buffer.getInt();
	// return new UpdateFirmwareCommand(messageNumber, deviceId);
	// }
	// private static HeaderMessage decodeCardListStatus(ByteBuffer buffer, byte
	// messageNumber) {
	// buffer.getShort();
	// byte listType = buffer.get();
	// int devID = buffer.getInt();
	// return new CardListStatusCommand(messageNumber, devID, listType);
	// }
	// private static HeaderMessage decodeSetCardIdentifier(ByteBuffer buffer, Byte
	// messageNumber) {
	// buffer.getShort();
	// int deviceId = buffer.getInt();
	// int quantId = buffer.getInt();
	// Collection<CardUseIdentifier> cardsUseId = new ArrayList<>(quantId);
	//
	// for (int i = 0; i < quantId; ++i) {
	// CardUseIdentifier cardUseId = new CardUseIdentifier(buffer.get());
	// CardType cardType = CardType.getCardType(buffer.get());
	// cardUseId.setCardUse(cardType);
	// cardsUseId.add(cardUseId);
	// }
	//
	// return new SetCardIdentifierCommand(messageNumber, cardsUseId, deviceId);
	// }

}
