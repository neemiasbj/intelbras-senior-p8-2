package br.com.domain.intelbras.model.api;

import br.com.domain.intelbras.abstraction.DeviceAbstract;
import br.com.domain.intelbras.model.enums.OperationUpdateDeviceEnum;
import jakarta.validation.constraints.NotBlank;

public class DeviceOperationModel extends DeviceAbstract {

	public DeviceOperationModel(@NotBlank(message = "Device IP address cannot be blank") String deviceIpAddress) {
		super(deviceIpAddress);
	}

	private OperationUpdateDeviceEnum operationType;
	private String model;

	public OperationUpdateDeviceEnum getOperationType() {
		return operationType;
	}

	public void setOperationType(OperationUpdateDeviceEnum operationType) {
		this.operationType = operationType;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

}
