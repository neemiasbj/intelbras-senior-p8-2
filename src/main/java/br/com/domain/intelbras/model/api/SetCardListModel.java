package br.com.domain.intelbras.model.api;

import java.util.List;

import br.com.domain.intelbras.abstraction.DeviceAbstract;
import jakarta.validation.constraints.NotBlank;

public class SetCardListModel extends DeviceAbstract {

	public SetCardListModel(@NotBlank(message = "Device IP address cannot be blank") String deviceIpAddress) {
		super(deviceIpAddress);
	}

	private List<String> cardsList;

	public List<String> getCardsList() {
		return cardsList;
	}

	public void setCardsList(List<String> cardsList) {
		this.cardsList = cardsList;
	}

	@Override
	public String toString() {
		return "[cardsList=" + cardsList + ", deviceIpAddress=" + getDeviceIpAddress() + "]";
	}

}
