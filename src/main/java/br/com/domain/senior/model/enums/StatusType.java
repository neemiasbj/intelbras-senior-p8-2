package br.com.domain.senior.model.enums;

public enum StatusType {
	DEVICE_STARTED("Dispositivo iniciado", 1), DEVICE_ONLINE("Dispositivo on-line", 2), DEVICE_OFFLINE("Dispositivo off-line", 3), PAPER_REEL_REPLACED("Bobina de papel trocada", 6), LACK_OF_PAPER("Falta de papel", 7), LOW_MEMORY("Pouca memória", 8), NO_MEMORY("Sem memória disponível", 9),
	INSSUANCE_OF_TIME_AND_ATTENDANCE_REPORT("Emissão de relação instantânea de marcações", 4), STARTED_USING_USB_FISCAL_PORT("Uso de porta fiscal USB", 5), BATTERY_REPLACED("Bateria trocada", 10), STARTED_USING_BATTERY("Iniciou uso da bateria", 11),
	STARTED_USING_ENERGY("Iniciou uso da energia principal", 12), DEVICE_LOCKED("Dispositivo bloqueado", 13), DEVICE_UNLOCKED("Dispositivo desbloqueado", 14), TAMPER("Dispositivo violado", 15);

	private final String description;
	private final byte id;

	private StatusType(String desc, int id) {
		this.description = desc;
		this.id = (byte) id;
	}

	public byte getId() {
		return this.id;
	}

	public String getDescription() {
		return this.description;
	}

	@Override
	public String toString() {
		return this.getDescription();
	}
}
