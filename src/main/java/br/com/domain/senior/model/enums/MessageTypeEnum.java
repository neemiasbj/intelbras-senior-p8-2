package br.com.domain.senior.model.enums;

public enum MessageTypeEnum {

	REQUEST_AUTHENTICATION(MessageCodeEnum.M01_01), REQUEST_DEVICE_DATA(MessageCodeEnum.M01_02), NOTIFY_ACCESS_EVENT(MessageCodeEnum.M02_01), REQUEST_ACCESS_VALIDATION(MessageCodeEnum.M02_02), ACCESS_VALIDATION_TIMEOUT(MessageCodeEnum.M02_03), NOTIFY_DEVICE_ALARMED(MessageCodeEnum.M03_01),
	NOTIFY_DEVICE_TIE_DEFECT(MessageCodeEnum.M03_02), NOTIFY_DEVICE_TIE_BLOCKED(MessageCodeEnum.M03_03), NOTIFY_DEVICE_EVENT(MessageCodeEnum.M03_04), NOTIFY_DEVICE_RESOURCE_STATUS(MessageCodeEnum.M03_05), DEVICE_DATE_TIME(MessageCodeEnum.M04_01), REQUEST_DATE_TIME(MessageCodeEnum.M04_04),
	IS_DRIVER_ALIVE(MessageCodeEnum.M04_06), SET_DEVICE_TIME_ZONE_PERMISSION_LIST(MessageCodeEnum.M05_01), SET_DEVICE_HOLIDAY_LIST(MessageCodeEnum.M05_02), ADD_SET_DEVICE_CARD_LIST(MessageCodeEnum.M05_03), DELETE_DEVICE_TIME_ZONE_PERMISSION_LIST(MessageCodeEnum.M05_04),
	DELETE_DEVICE_HOLIDAY_LIST(MessageCodeEnum.M05_05), DELETE_DEVICE_CARD_LIST(MessageCodeEnum.M05_06), DEVICE_TIME_ZONE_PERMISSION_LIST_STATUS(MessageCodeEnum.M05_07), DEVICE_HOLIDAY_LIST_STATUS(MessageCodeEnum.M05_08), DEVICE_CARD_LIST_STATUS(MessageCodeEnum.M05_09),
	REQUEST_BIOMETRIC_DATA(MessageCodeEnum.M05_16), INSERT_UPDATE_BIOMETRIC_DATA(MessageCodeEnum.M05_17), INSERT_UPDATE_BIOMETRIC_DATA_WITH_PIS(MessageCodeEnum.M05_24), PERSON_UPDATE_REP(MessageCodeEnum.M05_31), ADD_SET_DEVICE_CARD_LIST_REP(MessageCodeEnum.M05_32),
	UPDATE_DELETE_DEVICE(MessageCodeEnum.M05_33), UPDATE_BIOMETRIC_TECHNOLOGY(MessageCodeEnum.M05_34), UPDATE_DELETE_CORPORATION(MessageCodeEnum.M05_35), ADD_SET_BIOMETRIC_DATA(MessageCodeEnum.M05_36), DELETE_PERSON_BIOMETRY(MessageCodeEnum.M05_37), SET_CARD_IDENTIFIER(MessageCodeEnum.M05_38),
	ADD_SET_PASSWORD_LIST(MessageCodeEnum.M05_39), DELETE_PASSWORD_LIST(MessageCodeEnum.M05_40), ADD_SET_PLATE_LIST(MessageCodeEnum.M05_41), DELETE_PLATE_LIST(MessageCodeEnum.M05_42), FACIAL_CREDENTIAL_CHANGED(MessageCodeEnum.M05_43), SYNCHRONIZE_FACIAL_CREDENTIALS(MessageCodeEnum.M05_44),
	DEVICE_STATUS(MessageCodeEnum.M06_01), LOCK_DEVICE(MessageCodeEnum.M06_02), UNLOCK_DEVICE(MessageCodeEnum.M06_03), ACTIVATE_EMERGENCY(MessageCodeEnum.M06_04), DEACTIVATE_EMERGENCY(MessageCodeEnum.M06_05), ENABLE_DIGITAL_OUTPUT(MessageCodeEnum.M06_06),
	DISABLE_DIGITAL_OUTPUT(MessageCodeEnum.M06_07), DIGITAL_INPUT_STATUS(MessageCodeEnum.M06_08), COLLECT_EVENTS(MessageCodeEnum.M06_09), UPDATE_FIRMWARE(MessageCodeEnum.M06_16), COLLECT_BIOMETRIES(MessageCodeEnum.M06_17), SMART_CARD_MAP(MessageCodeEnum.M07_01), ACK(MessageCodeEnum.R00_00),
	NACK(MessageCodeEnum.R00_01), RECV(MessageCodeEnum.R00_03), DEVICE_DATA_RESPONSE(MessageCodeEnum.R00_04), UNDEVELOPED_COMMAND(MessageCodeEnum.R00_06), DRIVER_ALIVE_RESPONSE(MessageCodeEnum.R00_07), AUTHENTICATION_SUCCESSFUL(MessageCodeEnum.R01_01),
	ACCESS_VALIDATION_RESPONSE(MessageCodeEnum.R02_02), DATE_TIME_RESPONSE(MessageCodeEnum.R04_04), LIST_STATUS_RESPONSE(MessageCodeEnum.R05_00), BIOMETRIC_DATA_RESPONSE(MessageCodeEnum.R05_16), DEVICE_STATUS_RESPONSE(MessageCodeEnum.R06_01), DIGITAL_INPUT_STATUS_RESPONSE(MessageCodeEnum.R06_08),
	COLLECT_EVENTS_RESPONSE(MessageCodeEnum.R06_09);

	private final MessageCodeEnum messageCode;

	MessageTypeEnum(MessageCodeEnum messageCode) {
		this.messageCode = messageCode;
	}

	public String getValue() {
		return messageCode.getValue();
	}

	public String getDescription() {
		return messageCode.getDescription();
	}

	public String getCodeName() {
		return messageCode.name();
	}

	public MessageCodeEnum getMessageCode() {
		return messageCode;
	}

	@Override
	public String toString() {
		return "MessageTypeEnum{" + "value='" + getValue() + '\'' + ", description='" + getDescription() + '\'' + ", codeName='" + getCodeName() + '\'' + '}';
	}
}
