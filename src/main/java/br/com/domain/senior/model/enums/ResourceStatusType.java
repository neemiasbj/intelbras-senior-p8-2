package br.com.domain.senior.model.enums;

public enum ResourceStatusType {
	Memory("Memória", 1), Battery("Bateria", 2), PaperReel("Bobina de papel", 3);

	private final String description;
	private final byte id;

	private ResourceStatusType(String desc, int id) {
		this.description = desc;
		this.id = (byte) id;
	}

	public byte getId() {
		return this.id;
	}

	public String getDescription() {
		return this.description;
	}

	public String toString() {
		return this.getDescription();
	}
}
