package br.com.domain.intelbras.model.api;

import br.com.domain.intelbras.abstraction.DeviceAbstract;
import jakarta.validation.constraints.NotBlank;

public class DeviceBlockModel extends DeviceAbstract {

	public DeviceBlockModel(@NotBlank(message = "Device IP address cannot be blank") String deviceIpAddress) {
		super(deviceIpAddress);
		// TODO Auto-generated constructor stub
	}

	private String dateTime;

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	@Override
	public String toString() {
		return "IntelbrasDeviceBlockModel [dateTime=" + dateTime + ", getDeviceIpAddress()=" + getDeviceIpAddress() + "]";
	}

//	public enum BlockDeviceEnum {
//		DEVICE_BLOCK, DEVICE_UNBLOCK;
//	}
//
//	private BlockDeviceEnum blockType;
//
//	public BlockDeviceEnum getBlockType() {
//		return blockType;
//	}
//
//	public void setBlockType(BlockDeviceEnum blockType) {
//		this.blockType = blockType;
//	}

}
