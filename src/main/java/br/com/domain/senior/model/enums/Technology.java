package br.com.domain.senior.model.enums;

public enum Technology {
	UNKNOWN((byte) 0, "Desconhecida"), BARKEY((byte) 1, "Código de barras"), RFID((byte) 2, "Proximidade"), SMART((byte) 3, "Smart card"), BIOMETRIC((byte) 4, "Biometria");

	private final byte value;
	private final String explanation;

	private Technology(byte value, String explanation) {
		this.value = value;
		this.explanation = explanation;
	}

	public byte value() {
		return this.value;
	}

	public String toString() {
		return this.value + " - " + this.explanation;
	}

	public static Technology getByValue(byte tec) {
		Technology[] var1 = values();
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			Technology t = var1[var3];
			if (t.value == tec) {
				return t;
			}
		}

		return UNKNOWN;
	}
}
