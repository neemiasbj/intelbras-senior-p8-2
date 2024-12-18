package br.com.domain.intelbras.model.api;

public class GenericResponse {

	String response;

	public GenericResponse(String response) {
		this.response = response;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public Boolean isResponseValid() {
		if (response.equals("OK"))
			return true;
		return false;
	}

}
