package br.com.domain.intelbras.model.api;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class CardExcludeRequest {

	public enum Action {
		REMOVE_MULTI("removeMulti");

		private final String value;

		Action(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	public String buildUrl(String baseUrl, Action action) {
		return String.format("%s?action=%s", baseUrl, URLEncoder.encode(action.getValue(), StandardCharsets.UTF_8));
	}
}
