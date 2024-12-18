package br.com.domain.intelbras.model.enums;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

@JsonAdapter(OperationUpdateDeviceEnum.Adapter.class)
public enum OperationUpdateDeviceEnum {
	DEVICE_CREATED("DEVICE_CREATED"),

	DEVICE_UPDATED("DEVICE_UPDATED"),

	DEVICE_REMOVED("DEVICE_REMOVED"),

	DEVICE_CONFIG("DEVICE_CONFIG");

	private String value;

	OperationUpdateDeviceEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	public static OperationUpdateDeviceEnum fromValue(String text) {
		for (OperationUpdateDeviceEnum b : OperationUpdateDeviceEnum.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}

	public static class Adapter extends TypeAdapter<OperationUpdateDeviceEnum> {
		@Override
		public void write(final JsonWriter jsonWriter, final OperationUpdateDeviceEnum enumeration) throws IOException {
			jsonWriter.value(enumeration.getValue());
		}

		@Override
		public OperationUpdateDeviceEnum read(final JsonReader jsonReader) throws IOException {
			String value = jsonReader.nextString();
			return OperationUpdateDeviceEnum.fromValue(String.valueOf(value));
		}
	}
}
