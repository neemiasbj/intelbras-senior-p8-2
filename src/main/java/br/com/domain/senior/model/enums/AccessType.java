package br.com.domain.senior.model.enums;

public enum AccessType {
	ACCESS_GRANTED("ACCESS_GRANTED", "Acesso permitido", 1), ACCESS_DENIED_PLACE_INVALID("ACCESS_DENIED_PLACE_INVALID", "Acesso negado por local não permitido", 2), ACCESS_DENIED_SITUATION("ACCESS_DENIED_SITUATION", "Acesso negado pela situação", 3),
	ACCESS_DENIED_VALIDITY("ACCESS_DENIED_VALIDITY", "Acesso negado pela validade", 4), ACCESS_DENIED_LEVEL("ACCESS_DENIED_LEVEL", "Acesso negado pelo nível", 5), ACCESS_DENIED_INTERVAL_REMOVAL("ACCESS_DENIED_INTERVAL_REMOVAL", "Acesso negado por afastamento", 6),
	ACCESS_DENIED_CREDIT_ACCESS("ACCESS_DENIED_CREDIT_ACCESS", "Acesso negado por falta de créditos", 7), ACCESS_DENIED_INTERVAL_RANGE("ACCESS_DENIED_INTERVAL_RANGE", "Acesso negado por faixa horária", 8), ACCESS_DESISTENCE("ACCESS_DESISTENCE", "Desistência de acesso", 9),
	ACCESS_DENIED_PERMISSION_RANGE("ACCESS_DENIED_PERMISSION_RANGE", "Acesso negado pela faixa horária da permissão", 12), ACCESS_GRANTED_MASTER_CARD("ACCESS_GRANTED_MASTER_CARD", "Acesso valido crachá mestre", 10),
	ACCESS_DENIED_PERSON_TYPE("ACCESS_DENIED_PERSON_TYPE", "Acesso negado pelo tipo de pessoa", 13), ACCESS_GRANTED_OUT_OF_REPOSE("ACCESS_GRANTED_OUT_OF_REPOSE", "Acesso permitido, fora do estado de repouso", 14),
	ACCESS_GRANTED_FRAUD("ACCESS_GRANTED_FRAUD", "Acesso permitido, burla de catraca", 15), ACCESS_DENIED_PERMISSION_LIST("ACCESS_DENIED_PERMISSION_LIST", "Acesso negado pela lista de permissão", 16),
	ACCESS_DENIED_CARD_NOT_FOUND("ACCESS_DENIED_CARD_NOT_FOUND", "Acesso negado, cartão não encontrado", 17), ACCESS_DENIED_ON_EXIT_PERSON_TYPE("ACCESS_DENIED_ON_EXIT_PERSON_TYPE", "Acesso negado pelo tipo de pessoa, na saída", 22),
	ACCESS_DENIED_LUNCH_INTERVAL("ACCESS_DENIED_LUNCH_INTERVAL", "Acesso negado pelo intervalo de almoço", 24), ACCESS_DENIED_BLOCK_BY_FAULT("ACCESS_DENIED_BLOCK_BY_FAULT", "Acesso negado pelo bloqueio por falta", 25),
	ACCESS_DENIED_REST_TIME("ACCESS_DENIED_REST_TIME", "Acesso negado pela interjornada", 32), ACCESS_GRANTED_ACCOMPANY("ACCESS_GRANTED_ACCOMPANY", "Acesso permitido, acompanhante", 34), ACCESS_GRANTED_AUTHORIZER("ACCESS_GRANTED_AUTHORIZER", "Acesso permitido, autorizador", 35),
	ACCESS_DENIED_ACCOMPANY("ACCESS_DENIED_ACCOMPANY", "Acesso negado, acompanhante", 36), ACCESS_DENIED_INVALID_AUTHORIZER("ACCESS_DENIED_INVALID_AUTHORIZER", "Acesso negado, autorizador incorreto", 37),
	ACCESS_DENIED_WAITING_AUTHORIZER("ACCESS_DENIED_WAITING_AUTHORIZER", "Acesso negado, acompanhante deve aguardar autorizador", 38), ACCESS_DENIED_MINIMUM_STAY_TIME("ACCESS_DENIED_MINIMUM_STAY_TIME", "Acesso negado por tempo mínimo de permanência", 44),
	ACCESS_DENIED_ANTI_PASS_BACK("ACCESS_DENIED_ANTI_PASS_BACK", "Acesso negado por antidupla", 45), ACCESS_DENIED_CREDIT_RANGE("ACCESS_DENIED_CREDIT_RANGE", "Acesso negado, fora da faixa de crédito", 46), ACCESS_VALID_FRISK("ACCESS_VALID_FRISK", "Acesso com revista aleatória", 47),
	FRISK_DESISTENCE("FRISK_DESISTENCE", "Desistência da pessoa revistada", 48),

	ACCESS_VALID("ACCESS_VALID", "ACCESS_VALID", 1), ACCESS_DENIED_PERMISSION("ACCESS_DENIED_PERMISSION", "ACCESS_DENIED_PERMISSION", 0), ACCESS_DENIED_CARD_VALIDITY("ACCESS_DENIED_CARD_VALIDITY", "ACCESS_DENIED_CARD_VALIDITY", 0),
	ACCESS_DENIED_LEVEL_CONTROLER("ACCESS_DENIED_LEVEL_CONTROLER", "ACCESS_DENIED_LEVEL_CONTROLER", 0), ACCESS_DENIED_ROLE_RANGE("ACCESS_DENIED_ROLE_RANGE", "ACCESS_DENIED_ROLE_RANGE", 0), ACCESS_DENIED_LOCAL_RANGE("ACCESS_DENIED_LOCAL_RANGE", "ACCESS_DENIED_LOCAL_RANGE", 0),
	ACCESS_VALID_ACCOMPANY("ACCESS_VALID_ACCOMPANY", "ACCESS_VALID_ACCOMPANY", 0), ACCESS_DENIED_WAITING_FOR_NEXT_VALIDATION("ACCESS_DENIED_WAITING_FOR_NEXT_VALIDATION", "ACCESS_DENIED_WAITING_FOR_NEXT_VALIDATION", 0),
	ACCESS_DENIED_ANTI_PASSBACK("ACCESS_DENIED_ANTI_PASSBACK", "ACCESS_DENIED_ANTI_PASSBACK", 0), ACCESS_DENIED_STOCKING_CONTROL("ACCESS_DENIED_STOCKING_CONTROL", "ACCESS_DENIED_STOCKING_CONTROL", 0),
	ACCESS_DENIED_NOT_PARKING_SPACE_TYPE("ACCESS_DENIED_NOT_PARKING_SPACE_TYPE", "ACCESS_DENIED_NOT_PARKING_SPACE_TYPE", 0), ACCESS_DENIED_NOT_PARKING_SPACE("ACCESS_DENIED_NOT_PARKING_SPACE", "ACCESS_DENIED_NOT_PARKING_SPACE", 0), ACCESS_COERCION("ACCESS_COERCION", "ACCESS_COERCION", 0),
	ACCESS_DENIED_BIOMETRY("ACCESS_DENIED_BIOMETRY", "ACCESS_DENIED_BIOMETRY", 0), ACCESS_DENIED_CARD_FORMAT_ERROR("ACCESS_DENIED_CARD_FORMAT_ERROR", "ACCESS_DENIED_CARD_FORMAT_ERROR", 0), ACCESS_DENIED_FACILITY_CODE("ACCESS_DENIED_FACILITY_CODE", "ACCESS_DENIED_FACILITY_CODE", 0),
	ACCESS_DENIED_PASSWORD("ACCESS_DENIED_PASSWORD", "ACCESS_DENIED_PASSWORD", 0), ACCESS_DENIED_SECOND_CARD_NOT_PRESENTED("ACCESS_DENIED_SECOND_CARD_NOT_PRESENTED", "ACCESS_DENIED_SECOND_CARD_NOT_PRESENTED", 0), ACCESS_VALID_AUTHORIZER("ACCESS_VALID_AUTHORIZER", "ACCESS_VALID_AUTHORIZER", 0),
	ACCESS_VALID_FACILITY_CODE("ACCESS_VALID_FACILITY_CODE", "ACCESS_VALID_FACILITY_CODE", 0), ACCESS_VALID_FRAUD("ACCESS_VALID_FRAUD", "ACCESS_VALID_FRAUD", 0), ACCESS_VALID_OUT_REPOSE("ACCESS_VALID_OUT_REPOSE", "ACCESS_VALID_OUT_REPOSE", 0), ACCESS_DENIED("ACCESS_DENIED", "ACCESS_DENIED", 0),
	ACCESS_DENIED_CREDENTIAL_NOT_FOUND("ACCESS_DENIED_CREDENTIAL_NOT_FOUND", "ACCESS_DENIED_CREDENTIAL_NOT_FOUND", 0), ACCESS_DENIED_CREDENTIAL_VALIDITY("ACCESS_DENIED_CREDENTIAL_VALIDITY", "ACCESS_DENIED_CREDENTIAL_VALIDITY", 0),
	ACCESS_DENIED_INVALID_VEHICLE_CREDENTIAL_FORMAT("ACCESS_DENIED_INVALID_VEHICLE_CREDENTIAL_FORMAT", "ACCESS_DENIED_INVALID_VEHICLE_CREDENTIAL_FORMAT", 0),
	ACCESS_DENIED_INVALID_VEHICLE_CARD_CREDENTIAL("ACCESS_DENIED_INVALID_VEHICLE_CARD_CREDENTIAL", "ACCESS_DENIED_INVALID_VEHICLE_CARD_CREDENTIAL", 0), ACCESS_DENIED_WAITING_VEHICLE("ACCESS_DENIED_WAITING_VEHICLE", "ACCESS_DENIED_WAITING_VEHICLE", 0),
	ACCESS_DENIED_READER_NOT_VALIDATE_VEHICLE("ACCESS_DENIED_READER_NOT_VALIDATE_VEHICLE", "ACCESS_DENIED_READER_NOT_VALIDATE_VEHICLE", 0), ACCESS_DENIED_PERSON_NOT_ASSOCIATED_WITH_VEHICLE("ACCESS_DENIED_PERSON_NOT_ASSOCIATED_WITH_VEHICLE", "ACCESS_DENIED_PERSON_NOT_ASSOCIATED_WITH_VEHICLE", 0),
	ACCESS_DENIED_CUSTOM_VALIDATION("ACCESS_DENIED_CUSTOM_VALIDATION", "ACCESS_DENIED_CUSTOM_VALIDATION", 0), ACCESS_DENIED_BLOCK_PROVISORY_ON_EXIT("ACCESS_DENIED_BLOCK_PROVISORY_ON_EXIT", "ACCESS_DENIED_BLOCK_PROVISORY_ON_EXIT", 0),
	ACCESS_DENIED_CONTROLS_IN_BETWEEN_WORKDAYS("ACCESS_DENIED_CONTROLS_IN_BETWEEN_WORKDAYS", "ACCESS_DENIED_CONTROLS_IN_BETWEEN_WORKDAYS", 0), ACCESS_DENIED_WITHOUT_MASK("ACCESS_DENIED_WITHOUT_MASK", "ACCESS_DENIED_WITHOUT_MASK", 0),
	ACCESS_DENIED_ABNORMAL_TEMPERATURE("ACCESS_DENIED_ABNORMAL_TEMPERATURE", "ACCESS_DENIED_ABNORMAL_TEMPERATURE", 0);

	private final byte id;
	private final String value;
	private final String description;

	private AccessType(String value, String description, int id) {
		this.id = (byte) id;
		this.value = value;
		this.description = description;
	}

	public String getValue() {
		return value;
	}

	public String getDescription() {
		return this.description;
	}

	public byte getId() {
		return this.id;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	public static AccessType valueOf(byte returnType) {
		AccessType[] values = values();

		for (int i = 0; i < values.length; ++i) {
			if (values[i].id == returnType) {
				return values[i];
			}
		}

		return null;
	}
}
