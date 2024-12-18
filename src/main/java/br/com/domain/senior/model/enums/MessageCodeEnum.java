package br.com.domain.senior.model.enums;

import java.io.IOException;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

@JsonAdapter(MessageCodeEnum.Adapter.class)
public enum MessageCodeEnum {

	// Tipos de Mensagens
	// "Request Authentication" Solicitação de autenticação da comunicação.
	M01_01("M01_01", "Request Authentication"),

	// "Request Device Data" Solicitação de dados do dispositivo.
	M01_02("M01_02", "Request Device Data"),

	// "Notify Access Event" Notificação de evento de acesso.
	M02_01("M02_01", "Notify Access Event"),

	// "Request Access Validation" Solicitação de validação de acesso.
	M02_02("M02_02", "Request Access Validation"),

	// "Access Validation Timeout" Tempo limite para validação de acesso.
	M02_03("M02_03", "Access Validation Timeout"),

	// "Notify Device Alarmed" Notificação de alarme no dispositivo.
	M03_01("M03_01", "Notify Device Alarmed"),

	// "Notify Device Tie Defect" Notificação de defeito na intelbrasão do dispositivo.
	M03_02("M03_02", "Notify Device Tie Defect"),

	// "Notify Device Tie Blocked" Notificação de bloqueio na intelbrasão do
	// dispositivo.
	M03_03("M03_03", "Notify Device Tie Blocked"),

	// "Notify Device Event" Notificação de evento no dispositivo.
	M03_04("M03_04", "Notify Device Event"),

	// "Notify Device Resource Status" Notificação de status dos recursos do
	// dispositivo.
	M03_05("M03_05", "Notify Device Resource Status"),

	// "Device Date Time" Definição de data e hora do dispositivo.
	M04_01("M04_01", "Device Date Time"),

	// "Request Date Time" Solicitação de data e hora do dispositivo.
	M04_04("M04_04", "Request Date Time"),

	// "Is Driver Alive" Verificação se o driver está ativo.
	M04_06("M04_06", "Is Driver Alive"),

	// "Set Device Time Zone Permission List" Definir lista de permissões de zona
	// horária do dispositivo.
	M05_01("M05_01", "Set Device Time Zone Permission List"),

	// "Set Device Holiday List" Definir lista de feriados do dispositivo.
	M05_02("M05_02", "Set Device Holiday List"),

	// "Add/Set Device Card List" Adicionar/Definir lista de cartões do dispositivo.
	M05_03("M05_03", "Add/Set Device Card List"),

	// "Delete Device Time Zone Permission List" Excluir lista de permissões de zona
	// horária do dispositivo.
	M05_04("M05_04", "Delete Device Time Zone Permission List"),

	// "Delete Device Holiday List" Excluir lista de feriados do dispositivo.
	M05_05("M05_05", "Delete Device Holiday List"),

	// "Delete Device Card List" Excluir lista de cartões do dispositivo.
	M05_06("M05_06", "Delete Device Card List"),

	// "Device Time Zone Permission List Status" Status da lista de permissões de
	// zona horária do dispositivo.
	M05_07("M05_07", "Device Time Zone Permission List Status"),

	// "Device Holiday List Status" Status da lista de feriados do dispositivo.
	M05_08("M05_08", "Device Holiday List Status"),

	// "Device Card List Status" Status da lista de cartões do dispositivo.
	M05_09("M05_09", "Device Card List Status"),

	// "Request Biometric Data" Solicitação de dados biométricos.
	M05_16("M05_16", "Request Biometric Data"),

	// "Insert/Update Biometric Data" Inserir/Atualizar dados biométricos.
	M05_17("M05_17", "Insert/Update Biometric Data"),

	// "Insert/Update Biometric Data with PIS" Inserir/Atualizar dados biométricos
	// com PIS.
	M05_24("M05_24", "Insert/Update Biometric Data with PIS"),

	// "Person Update REP" Atualização de pessoa no REP.
	M05_31("M05_31", "Person Update REP"),

	// "Add/Set Device Card List REP" Adicionar/Definir lista de cartões do
	// dispositivo no REP.
	M05_32("M05_32", "Add/Set Device Card List REP"),

	// "Update/Delete Device" Atualizar/Excluir dispositivo.
	M05_33("M05_33", "Update/Delete Device"),

	// "Update Biometric Technology" Atualizar tecnologia biométrica.
	M05_34("M05_34", "Update Biometric Technology"),

	// "Update/Delete Corporation" Atualizar/Excluir corporação.
	M05_35("M05_35", "Update/Delete Corporation"),

	// "Add/Set Biometric Data" Adicionar/Definir dados biométricos.
	M05_36("M05_36", "Add/Set Biometric Data"),

	// "Delete Person Biometry" Excluir biometria da pessoa.
	M05_37("M05_37", "Delete Person Biometry"),

	// "Set Card Identifier" Definir identificador de cartão.
	M05_38("M05_38", "Set Card Identifier"),

	// "Add/Set Password List" Adicionar/Definir lista de senhas.
	M05_39("M05_39", "Add/Set Password List"),

	// "Delete Password List" Excluir lista de senhas.
	M05_40("M05_40", "Delete Password List"),

	// "Add/Set Plate List" Adicionar/Definir lista de placas.
	M05_41("M05_41", "Add/Set Plate List"),

	// "Delete Plate List" Excluir lista de placas.
	M05_42("M05_42", "Delete Plate List"),

	// "Facial Credential Changed" Credencial facial alterada.
	M05_43("M05_43", "Facial Credential Changed"),

	// "Synchronize Facial Credentials" Sincronizar credenciais faciais.
	M05_44("M05_44", "Synchronize Facial Credentials"),

	// "Device Status" Status do dispositivo.
	M06_01("M06_01", "Device Status"),

	// "Lock Device" Bloquear dispositivo.
	M06_02("M06_02", "Lock Device"),

	// "Unlock Device" Desbloquear dispositivo.
	M06_03("M06_03", "Unlock Device"),

	// "Activate Emergency" Ativar emergência.
	M06_04("M06_04", "Activate Emergency"),

	// "Deactivate Emergency" Desativar emergência.
	M06_05("M06_05", "Deactivate Emergency"),

	// "Enable Digital Output" Habilitar saída digital.
	M06_06("M06_06", "Enable Digital Output"),

	// "Disable Digital Output" Desabilitar saída digital.
	M06_07("M06_07", "Disable Digital Output"),

	// "Digital Input Status" Status da entrada digital.
	M06_08("M06_08", "Digital Input Status"),

	// "Collect Events" Coletar eventos.
	M06_09("M06_09", "Collect Events"),

	// "Update Firmware" Atualizar firmware.
	M06_16("M06_16", "Update Firmware"),

	// "Collect Biometries" Coletar biometrias.
	M06_17("M06_17", "Collect Biometries"),

	// "Smart Card Map" Mapa do Smart Card.
	M07_01("M07_01", "Smart Card Map"),

	// Tipos de Resposta
	// "ACK" Indica que a mensagem foi executada corretamente.
	R00_00("R00_00", "ACK"),

	// "NACK" Indica que a mensagem foi recebida e não foi aceita.
	R00_01("R00_01", "NACK"),

	// "RECV" Indica que a mensagem foi recebida e aceita corretamente.
	R00_03("R00_03", "RECV"),

	// "Device Data Response" Resposta com dados do dispositivo.
	R00_04("R00_04", "Device Data Response"),

	// "Undeveloped Command" Comando não desenvolvido.
	R00_06("R00_06", "Undeveloped Command"),

	// "Driver Alive Response" Resposta de status do driver.
	R00_07("R00_07", "Driver Alive Response"),

	// "Authentication Successful" Autenticação bem-sucedida.
	R01_01("R01_01", "Authentication Successful"),

	// "Access Validation Response" Resposta de validação de acesso.
	R02_02("R02_02", "Access Validation Response"),

	// "Date Time Response" Resposta de data e hora.
	R04_04("R04_04", "Date Time Response"),

	// "List Status Response" Resposta de status da lista.
	R05_00("R05_00", "List Status Response"),

	// "Biometric Data Response" Resposta com dados biométricos.
	R05_16("R05_16", "Biometric Data Response"),

	// "Device Status Response" Resposta de status do dispositivo.
	R06_01("R06_01", "Device Status Response"),

	// "Digital Input Status" Status da entrada digital.
	R06_08("R06_08", "Digital Input Status"),

	// "Collect Events" Coletar eventos.
	R06_09("R06_09", "Collect Events");

	private final String value;
	private final String description;

	MessageCodeEnum(String value, String description) {
		this.value = value;
		this.description = description;
	}

	public String getValue() {
		return value;
	}

	public String getDescription() {
		return description;
	}

	public static MessageCodeEnum fromValue(String text) {
		for (MessageCodeEnum b : MessageCodeEnum.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("value", value).append("description", description).toString();
	}

	public static class Adapter extends TypeAdapter<MessageCodeEnum> {
		@Override
		public void write(final JsonWriter jsonWriter, final MessageCodeEnum enumeration) throws IOException {
			jsonWriter.value(enumeration.getValue());
		}

		@Override
		public MessageCodeEnum read(final JsonReader jsonReader) throws IOException {
			String value = jsonReader.nextString();
			return MessageCodeEnum.fromValue(String.valueOf(value));
		}
	}
}
