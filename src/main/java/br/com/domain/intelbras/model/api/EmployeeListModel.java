package br.com.domain.intelbras.model.api;

import java.util.List;

import br.com.domain.intelbras.abstraction.DeviceAbstract;
import jakarta.validation.constraints.NotBlank;

public class EmployeeListModel extends DeviceAbstract {

	protected EmployeeListModel(
			@NotBlank(message = "Device IP address cannot be blank") String deviceIpAddress) {
		super(deviceIpAddress);
		// TODO Auto-generated constructor stub
	}

	private List<REPEmployeeModel> employeesList;

	@Override
	public String toString() {
		return "IntelbrasREP2000EmployeeListModel [employeesList=" + employeesList + ", deviceIpAddress="
				+ getDeviceIpAddress() + "]";
	}

	public List<REPEmployeeModel> getEmployeesList() {
		return employeesList;
	}

	public void setEmployeesList(List<REPEmployeeModel> employeesList) {
		this.employeesList = employeesList;
	}

}
