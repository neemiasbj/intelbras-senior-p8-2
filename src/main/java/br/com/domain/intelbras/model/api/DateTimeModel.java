package br.com.domain.intelbras.model.api;

import br.com.domain.intelbras.abstraction.DeviceAbstract;
import jakarta.validation.constraints.NotBlank;

public class DateTimeModel extends DeviceAbstract {

	public DateTimeModel(@NotBlank(message = "Device IP address cannot be blank") String deviceIpAddress) {
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
		return "IntelbrasDateTimeModel [dateTime=" + dateTime + "]";
	}

}
