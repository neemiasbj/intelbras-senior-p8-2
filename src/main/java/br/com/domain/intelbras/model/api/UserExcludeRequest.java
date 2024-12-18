package br.com.domain.intelbras.model.api;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class UserExcludeRequest {
	private final List<Integer> userIds;

	public UserExcludeRequest() {
		this.userIds = new ArrayList<>();
	}

	public void addUserId(int userId) {
		this.userIds.add(userId);
	}

	public String buildUrl(String baseUrl, String action) {
		StringBuilder urlBuilder = new StringBuilder(baseUrl);
		urlBuilder.append("?action=").append(URLEncoder.encode(action, StandardCharsets.UTF_8));

		for (int i = 0; i < userIds.size(); i++) {
			String key = String.format("UserIDList[%d]", i);
			String value = String.valueOf(userIds.get(i));
			urlBuilder.append("&").append(URLEncoder.encode(key, StandardCharsets.UTF_8)).append("=")
					.append(URLEncoder.encode(value, StandardCharsets.UTF_8));
		}

		return urlBuilder.toString();
	}
}
