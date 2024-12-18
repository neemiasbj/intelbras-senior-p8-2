package br.com.domain.intelbras.model.api;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class DoorChangeStateRequest {
	private final List<DoorState> states;

	public DoorChangeStateRequest() {
		this.states = new ArrayList<>();
	}

	public enum DoorState {
		NORMAL("Normal"), CLOSE_ALWAYS("CloseAlways"), OPEN_ALWAYS("OpenAlways");

		private final String state;

		DoorState(String state) {
			this.state = state;
		}

		public String getState() {
			return state;
		}
	}

	public void addState(DoorState state) {
		this.states.add(state);
	}

	public String buildUrl(String baseUrl, String action) {
		StringBuilder urlBuilder = new StringBuilder(baseUrl);
		urlBuilder.append("?action=").append(URLEncoder.encode(action, StandardCharsets.UTF_8));

		for (int i = 0; i < states.size(); i++) {
			String key = String.format("AccessControl[%d].State", i);
			String value = states.get(i).getState();
			urlBuilder.append("&").append(URLEncoder.encode(key, StandardCharsets.UTF_8)).append("=")
					.append(URLEncoder.encode(value, StandardCharsets.UTF_8));
		}

		return urlBuilder.toString();
	}
}
