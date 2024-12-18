package br.com.domain.senior.model.enums;

public enum VerifyTimeKeepAttendanceRange {
	NONE((byte) 0, "Não verifica"), ACCESS_TIMEKEEP((byte) 1, "Verifica faixas de acesso e ponto"), ACCESS_ONLY((byte) 2, "Verifica faixas de acesso"), TIMEKEEP_ONLY((byte) 3, "Verifica faixas de ponto");

	private final byte value;
	private final String explanation;

	private VerifyTimeKeepAttendanceRange(byte value, String explanation) {
		this.value = value;
		this.explanation = explanation;
	}

	public byte value() {
		return this.value;
	}

	public String toString() {
		return this.value + " - " + this.explanation;
	}

	public static VerifyTimeKeepAttendanceRange getByValue(byte timeKeepValue) {
		VerifyTimeKeepAttendanceRange[] var1 = values();
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			VerifyTimeKeepAttendanceRange vtkar = var1[var3];
			if (vtkar.value == timeKeepValue) {
				return vtkar;
			}
		}

		return NONE;
	}
}
