package br.com.domain.senior.model.enums;

public enum Situation {
	BLOCKED((byte) 1, "Bloqueado"), AUTHORIZED((byte) 10, "Liberado");

	private final byte value;
	private final String explanation;

	private Situation(byte value, String explanation) {
		this.value = value;
		this.explanation = explanation;
	}

	public byte value() {
		return this.value;
	}

	public String toString() {
		return this.value + " - " + this.explanation;
	}

	public static Situation getByValue(byte situation) {
		Situation[] var1 = values();
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			Situation s = var1[var3];
			if (s.value == situation) {
				return s;
			}
		}

		return BLOCKED;
	}
}
