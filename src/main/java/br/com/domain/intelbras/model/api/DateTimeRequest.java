package br.com.domain.intelbras.model.api;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DateTimeRequest {

	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@JsonProperty("currentTime")
	private String currentTime;

	public String getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(String currentTime) {
		this.currentTime = currentTime;
	}

	public enum Action {
		GET_DATE_TIME("getCurrentTime"), SET_DATE_TIME("setCurrentTime");

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

	public String buildUrl(String baseUrl, Action action, String dateTimeToSet) {
		return String.format("%s?action=%s&time=%s", baseUrl,
				URLEncoder.encode(action.getValue(), StandardCharsets.UTF_8),
				URLEncoder.encode(dateTimeToSet, StandardCharsets.UTF_8));
	}

	public Date extractDate(String plainTextResponse) throws Exception {
		String prefix = "result=";

		if (plainTextResponse.startsWith(prefix)) {
			String dateString = plainTextResponse.substring(prefix.length()).trim();
			return dateFormat.parse(dateString);
		} else {
			throw new IllegalArgumentException(
					"Invalid Format: the text doesn't contains the expected format prefix: result=");
		}
	}

	public Boolean isValidDate(String plainTextResponse) throws Exception {
		String prefix = "result=";

		if (plainTextResponse.startsWith(prefix))
			return true;
		else
//			throw new IllegalArgumentException("Invalid Format: the text doesn't contains the expected format prefix: result=");
			return false;

	}
}
