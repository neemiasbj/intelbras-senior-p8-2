package br.com.domain.intelbras.model.api;

import br.com.domain.intelbras.model.enums.WebhookTypeMessageEnum;

public class ResponseDeviceGenericModel {

	private boolean error;
	private String pendencyId;
	private String message;
	private WebhookTypeMessageEnum responseMessageType;
	private Object data;

	public String getPendencyId() {
		return pendencyId;
	}

	public void setPendencyId(String pendencyId) {
		this.pendencyId = pendencyId;
	}

	public Boolean getError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public WebhookTypeMessageEnum getResponseMessageType() {
		return responseMessageType;
	}

	public void setResponseMessageType(WebhookTypeMessageEnum responseMessageType) {
		this.responseMessageType = responseMessageType;
	}

	@Override
	public String toString() {
		return "DeviceGenericModel [error=" + error + ", pendencyId=" + pendencyId + ", message=" + message + ", responseMessageType=" + responseMessageType + ", data=" + data + "]";
	}

}
