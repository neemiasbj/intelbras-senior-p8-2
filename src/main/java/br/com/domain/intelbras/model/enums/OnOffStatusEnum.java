package br.com.domain.intelbras.model.enums;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public enum OnOffStatusEnum {
	ONLINE("ONLINE"),

	OFFLINE("OFFLINE");

	private String value;

	OnOffStatusEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	public static OnOffStatusEnum fromValue(String text) {
		for (OnOffStatusEnum b : OnOffStatusEnum.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}

	public static class Adapter extends TypeAdapter<OnOffStatusEnum> {
		@Override
		public void write(final JsonWriter jsonWriter, final OnOffStatusEnum enumeration) throws IOException {
			jsonWriter.value(enumeration.getValue());
		}

		@Override
		public OnOffStatusEnum read(final JsonReader jsonReader) throws IOException {
			String value = jsonReader.nextString();
			return OnOffStatusEnum.fromValue(String.valueOf(value));
		}
	}
}
