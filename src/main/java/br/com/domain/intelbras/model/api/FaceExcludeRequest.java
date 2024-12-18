package br.com.domain.intelbras.model.api;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class FaceExcludeRequest {

	private List<Integer> userIdList;

	public FaceExcludeRequest(List<Integer> userIdList) {
		this.userIdList = userIdList;
	}

	public List<Integer> getUserIdList() {
		return userIdList;
	}

	public void setUserIdList(List<Integer> userIdList) {
		this.userIdList = userIdList;
	}

	public String buildUrl(String baseUrl, String action) {
		StringBuilder urlBuilder = new StringBuilder(baseUrl);
		urlBuilder.append("?action=").append(URLEncoder.encode(action, StandardCharsets.UTF_8));
		for (int i = 0; i < userIdList.size(); i++) {
			urlBuilder.append("&UserIDList[").append(i).append("]=").append(userIdList.get(i));
		}
		return urlBuilder.toString();
	}
}
