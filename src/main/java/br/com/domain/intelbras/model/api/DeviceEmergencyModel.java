package br.com.domain.intelbras.model.api;

import br.com.domain.intelbras.abstraction.DeviceAbstract;
import jakarta.validation.constraints.NotBlank;

public class DeviceEmergencyModel extends DeviceAbstract {

	public DeviceEmergencyModel(@NotBlank(message = "Device IP address cannot be blank") String deviceIpAddress) {
		super(deviceIpAddress);
		// TODO Auto-generated constructor stub
	}

//	public enum EmergencyDeviceEnum {
//		DEVICE_SET_EMERGENCY, DEVICE_UNSET_EMERGENCY;
//	}
//
//	private EmergencyDeviceEnum emercencyType;
//
//	public EmergencyDeviceEnum getEmercencyType() {
//		return emercencyType;
//	}
//
//	public void setEmercencyType(EmergencyDeviceEnum emercencyType) {
//		this.emercencyType = emercencyType;
//	}
//
//	@Override
//	public String toString() {
//		return "[emercencyType=" + emercencyType + "]";
//	}

}
