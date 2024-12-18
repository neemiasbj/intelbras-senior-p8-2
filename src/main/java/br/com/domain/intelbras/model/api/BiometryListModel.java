package br.com.domain.intelbras.model.api;

import java.util.List;

import br.com.domain.intelbras.abstraction.DeviceAbstract;
import jakarta.validation.constraints.NotBlank;

public class BiometryListModel extends DeviceAbstract {

	private List<BiometryModel> biometriesList;

	public List<BiometryModel> getBiometriesList() {
		return biometriesList;
	}

	public void setBiometriesList(List<BiometryModel> biometriesList) {
		this.biometriesList = biometriesList;
	}

	@Override
	public String toString() {
		return "IntelbrasBiometryListModel [biometriesList=" + biometriesList + "]";
	}

	public BiometryListModel(List<BiometryModel> biometriesList,
			@NotBlank(message = "Device IP address cannot be blank") String deviceIpAddress) {
		super(deviceIpAddress);
		this.biometriesList = biometriesList;
	}

	public BiometryListModel(@NotBlank(message = "Device IP address cannot be blank") String deviceIpAddress) {
		super(deviceIpAddress);
	}

}
