package br.com.domain.senior.model.enums;

import br.com.domain.intelbras.model.enums.AccessDirectionEnum;

public enum DirectionsType {
	BOTH("Entrada e Saída", 0), ENTRANCE("Entrada", 0), EXIT("Saída", 1);

	private final String description;
	private final byte id;

	DirectionsType(String desc, int id) {
		this.description = desc;
		this.id = (byte) id;
	}

	public byte getId() {
		return this.id;
	}

	public static DirectionsType valueOf(byte returnType) {
		for (DirectionsType type : values()) {
			if (type.id == returnType) {
				return type;
			}
		}
		return null;
	}

	public static DirectionsType valueOfStringType(AccessDirectionEnum returnType) {
		for (DirectionsType type : values()) {
			if (type.name().equals(returnType.name())) {
				return type;
			}
		}
		return null;
	}

	public boolean valueOfString(String typeString) {
		for (DirectionsType type : values()) {
			if (type.toString().equals(typeString)) {
				return true;
			}
		}
		return false;
	}

	public String getDescription() {
		return this.description;
	}

	@Override
	public String toString() {
		return this.getDescription();
	}
}