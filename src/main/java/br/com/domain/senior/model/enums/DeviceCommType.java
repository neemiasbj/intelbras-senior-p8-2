package br.com.domain.senior.model.enums;

import br.com.domain.intelbras.model.enums.OnOffStatusEnum;

public enum DeviceCommType {
	ONLINE("Online", 1), OFFLINE("Offline", 0);

	private final String description;
	private final byte id;

	private DeviceCommType(String desc, int id) {
		this.description = desc;
		this.id = (byte) id;
	}

	public byte getId() {
		return this.id;
	}

	public String getDescription() {
		return this.description;
	}

	public static OnOffStatusEnum fromValue(String text) {
		for (OnOffStatusEnum b : OnOffStatusEnum.values()) {
			if (String.valueOf(b).equals(text)) {
				return b;
			}
		}
		return null;
	}

	public static DeviceCommType fromValue(byte id) {
		for (DeviceCommType b : DeviceCommType.values()) {
			if (b.getId() == id) {
				return b;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return this.getDescription();
	}
}
