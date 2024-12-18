package br.com.domain.senior.model.enums;

public enum AlarmType {
	DEVICE_ALARMED("Dispositivo alarmado"), DEVICE_TIE_BLOCKED("Bloqueio de laço"), DEVICE_TIE_DEFECT("Defeito de laço");

	private final String description;

	private AlarmType(String desc) {
		this.description = desc;
	}

	public String getDescription() {
		return this.description;
	}

	public String toString() {
		return this.getDescription();
	}
}
