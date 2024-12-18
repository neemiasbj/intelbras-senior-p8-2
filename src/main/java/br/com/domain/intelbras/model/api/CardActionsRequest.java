package br.com.domain.intelbras.model.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CardActionsRequest {

	@JsonProperty("CardList")
	private List<Card> cardList;

	public CardActionsRequest() {
		this.cardList = new ArrayList<>();
	}

	public List<Card> getCardList() {
		return cardList;
	}

	public void setCardList(List<Card> cardList) {
		this.cardList = cardList;
	}

	public void addCard(Card card) {
		this.cardList.add(card);
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

	public static class Card {
		@JsonProperty("UserID")
		private String userId;

		@JsonProperty("CardNo")
		private String cardNo;

		@JsonProperty("CardType")
		private CardType cardType;

		@JsonProperty("CardStatus")
		private CardStatus cardStatus;

		public Card(String userId, String cardNo, CardType cardType, CardStatus cardStatus) {
			this.userId = userId;
			this.cardNo = cardNo;
			this.cardType = cardType;
			this.cardStatus = cardStatus;
		}

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public String getCardNo() {
			return cardNo;
		}

		public void setCardNo(String cardNo) {
			this.cardNo = cardNo;
		}

		public CardType getCardType() {
			return cardType;
		}

		public void setCardType(CardType cardType) {
			this.cardType = cardType;
		}

		public CardStatus getCardStatus() {
			return cardStatus;
		}

		public void setCardStatus(CardStatus cardStatus) {
			this.cardStatus = cardStatus;
		}
	}

	public enum CardType {
		ORDINARY(0), VIP(1), GUEST(2), PATROL(3), BLOCKLIST(4), DURESS(5);

		private final int value;

		CardType(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

	public enum CardStatus {
		NORMAL(0), CANCELED(1), FROZEN(2);

		private final int value;

		CardStatus(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

	public String buildUrl(String baseUrl, Action action) {
		return String.format("%s?action=%s", baseUrl, URLEncoder.encode(action.getValue(), StandardCharsets.UTF_8));
	}
}
