package br.com.domain.intelbras.model.enums;

public enum DeviceControllerEndpoints {
	ADD_ALLOWED_CARDS_LIST("/device/addAllowedCardsList"), ADD_BIOMETRIES("/device/addBiometries"), ADD_BLOCKED_CARDS_LIST("/device/addBlockedCardsList"), ADD_EMPLOYEES_LIST("/device/addEmployeesList"), ADD_VISITOR_CARDS_LIST("/device/addVisitorCardsList"),
	BIOMETRIES_DELETE_BY_LIST("/device/biometriesDeleteByList"), DELETE_ALL_BIOMETRIES("/device/deleteAllBiometries"), EXCLUDE_ALLOWED_CARDS_LIST("/device/excludeAllowedCardsList"), GET_CONNECTED_DEVICES("/device/getConnectedDevices"), GET_CONNECTED_DEVICES_COUNT("/device/getConnectedDevicesCount"),
	GET_STATUS("/device/getStatus"), GET_UNLOCK_STATUS("/device/getUnlockStatus"), REMOVE_ALL_EMPLOYEES("/device/removeAllEmployees"), REMOVE_EMPLOYEES_BY_LIST("/device/removeEmployeesByList"), SET_APPLICATION("/device/setApplication"), SET_DATE_TIME("/device/setDateTime"),
	SET_EMERGENCY_STATUS("/device/setEmergencyStatus"), SET_EMPLOYER("/device/setEmployer"), UNLOCK_DEVICE("/device/unlockDevice"), UNSET_EMERGENCY_STATUS("/device/unsetEmergencyStatus");

	private final String path;

	DeviceControllerEndpoints(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}
}
