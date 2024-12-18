package br.com.domain.intelbras.model.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FaceIncludeRequest {

	@JsonProperty("FaceList")
	private List<Face> faceList;

	public FaceIncludeRequest() {
		this.faceList = new ArrayList<>();
	}

	public List<Face> getFaceList() {
		return faceList;
	}

	public void setFaceList(List<Face> faceList) {
		this.faceList = faceList;
	}

	public void addFace(Face face) {
		this.faceList.add(face);
	}

	public static class Face {
		@JsonProperty("UserID")
		private String userId;

		@JsonProperty("PhotoData")
		private List<String> photoData;

		public Face(String userId, List<String> photoData) {
			this.userId = userId;
			this.photoData = photoData;
		}

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public List<String> getPhotoData() {
			return photoData;
		}

		public void setPhotoData(List<String> photoData) {
			this.photoData = photoData;
		}
	}

	public enum Action {
		INSERT_MULTI("insertMulti");

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
