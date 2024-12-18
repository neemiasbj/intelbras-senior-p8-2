package br.com.domain.intelbras.model.api;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class UserGetByCardsRequest {
	private final List<String> cardNos;

	public UserGetByCardsRequest() {
		this.cardNos = new ArrayList<>();
	}

	public void addCardNo(String cardNo) {
		this.cardNos.add(cardNo);
	}

	public String buildUrl(String baseUrl, String action) {
		StringBuilder urlBuilder = new StringBuilder(baseUrl);
		urlBuilder.append("?action=").append(URLEncoder.encode(action, StandardCharsets.UTF_8));

		for (int i = 0; i < cardNos.size(); i++) {
			String key = String.format("CardNoList[%d]", i);
			String value = cardNos.get(i);
			urlBuilder.append("&").append(URLEncoder.encode(key, StandardCharsets.UTF_8)).append("=")
					.append(URLEncoder.encode(value, StandardCharsets.UTF_8));
		}

		return urlBuilder.toString();
	}
}
