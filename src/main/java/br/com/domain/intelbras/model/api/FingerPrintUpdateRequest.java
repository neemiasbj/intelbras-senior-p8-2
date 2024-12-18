package br.com.domain.intelbras.model.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FingerPrintUpdateRequest {

	@JsonProperty("vecPackets")
	private List<String> vecPackets;

	@JsonProperty("AccessFingerprints")
	private List<AccessFingerprint> accessFingerprints;

	public FingerPrintUpdateRequest() {
		this.vecPackets = new ArrayList<>();
		this.accessFingerprints = new ArrayList<>();
	}

	public List<String> getVecPackets() {
		return vecPackets;
	}

	public void setVecPackets(List<String> vecPackets) {
		this.vecPackets = vecPackets;
	}

	public List<AccessFingerprint> getAccessFingerprints() {
		return accessFingerprints;
	}

	public void setAccessFingerprints(List<AccessFingerprint> accessFingerprints) {
		this.accessFingerprints = accessFingerprints;
	}

	public void addPacket(String packet) {
		this.vecPackets.add(packet);
	}

	public void addFingerprint(AccessFingerprint fingerprint) {
		this.accessFingerprints.add(fingerprint);
	}

	public static class AccessFingerprint {

		@JsonProperty("UserID")
		private int userId;

		@JsonProperty("FingerprintPacket")
		private FingerprintPacket fingerprintPacket;

		public AccessFingerprint(int userId, FingerprintPacket fingerprintPacket) {
			this.userId = userId;
			this.fingerprintPacket = fingerprintPacket;
		}

		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}

		public FingerprintPacket getFingerprintPacket() {
			return fingerprintPacket;
		}

		public void setFingerprintPacket(FingerprintPacket fingerprintPacket) {
			this.fingerprintPacket = fingerprintPacket;
		}
	}

	public static class FingerprintPacket {

		@JsonProperty("Length")
		private int length;

		@JsonProperty("Count")
		private int count;

		@JsonProperty("DuressIndex")
		private int duressIndex;

		public FingerprintPacket(int length, int count, int duressIndex) {
			this.length = length;
			this.count = count;
			this.duressIndex = duressIndex;
		}

		public int getLength() {
			return length;
		}

		public void setLength(int length) {
			this.length = length;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

		public int getDuressIndex() {
			return duressIndex;
		}

		public void setDuressIndex(int duressIndex) {
			this.duressIndex = duressIndex;
		}
	}

	public enum Action {
		UPDATE_MULTI("updateMulti");

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
