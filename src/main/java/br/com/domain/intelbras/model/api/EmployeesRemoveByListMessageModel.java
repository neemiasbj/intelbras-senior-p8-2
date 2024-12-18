package br.com.domain.intelbras.model.api;

import java.util.List;

import br.com.domain.intelbras.abstraction.DeviceAbstract;
import jakarta.validation.constraints.NotBlank;

public class EmployeesRemoveByListMessageModel extends DeviceAbstract {

	private List<String> registriesList;

	public EmployeesRemoveByListMessageModel(
			@NotBlank(message = "Device IP address cannot be blank") String deviceIpAddress) {
		super(deviceIpAddress);
		// TODO Auto-generated constructor stub
	}

	public List<String> getRegistriesList() {
		return registriesList;
	}

	public void setRegistriesList(List<String> registriesList) {
		this.registriesList = registriesList;
	}

	@Override
	public String toString() {
		return "ApiDeviceEmployeesRemoveByListMessage [registriesLis=" + registriesList + ", getDeviceIpAddress()="
				+ getDeviceIpAddress() + "]";
	}

}
