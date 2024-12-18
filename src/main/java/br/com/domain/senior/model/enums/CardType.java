package br.com.domain.senior.model.enums;

public enum CardType {
	NONE(0, "Nenhum"), EMPLOYEE(1, "Empregado"), THIRD_PARTY(2, "Terceiro"), PARTNER(3, "Parceiro"), VISITOR(4, "Visitante, grupo de visitante"), ANOTHER_UNIT(5, "Outra unidade"), PROVISORY(6, "Provisório"), RESPONSIBLE(7, "Responsável de aluno"), MASTER_CARD(8, "Crachá mestre"),
	PATIENT(9, "Paciente"), STUDENT(10, "Aluno"), ACCOMPANY_PATIENT(11, "Acompanhante de paciente"), AUTHORIZED(12, "Autorização de entrada"), CANDIDATE(13, "Candidato"), OTHER(16, "Outro");

	private final byte id;
	private final String description;

	private CardType(int id, String description) {
		this.id = (byte) id;
		this.description = description;
	}

	public byte getId() {
		return this.id;
	}

	public String getDescription() {
		return this.description;
	}

	public String toString() {
		return String.format("%02d - %s", this.getId(), this.getDescription());
	}

	public static CardType getCardType(byte value) {
		CardType[] var1 = values();
		int var2 = var1.length;

		for (int var3 = 0; var3 < var2; ++var3) {
			CardType type = var1[var3];
			if (type.getId() == value) {
				return type;
			}
		}

		return NONE;
	}
}
