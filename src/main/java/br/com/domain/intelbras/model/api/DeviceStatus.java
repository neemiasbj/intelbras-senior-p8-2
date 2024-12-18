package br.com.domain.intelbras.model.api;

import br.com.domain.intelbras.abstraction.DeviceAbstract;
import jakarta.validation.constraints.NotBlank;

public class DeviceStatus extends DeviceAbstract {

	public DeviceStatus(@NotBlank(message = "Device IP address cannot be blank") String deviceIpAddress) {
		super(deviceIpAddress);
		// TODO Auto-generated constructor stub
	}
}
