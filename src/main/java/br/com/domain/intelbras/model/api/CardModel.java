package br.com.domain.intelbras.model.api;

import br.com.util.Utils;

public class CardModel {

	private String cardNumber;

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		if (cardNumber != null) {
			this.cardNumber = Utils.padStart(cardNumber, 12, '0');
			;
		}
	}

	@Override
	public String toString() {
		return cardNumber;
	}

}
