package br.com.domain.intelbras.model.api;

public class REPEmployeeModel {

	private String registry;
	private String name;
	private String pis;
	private String cpf;

	public String getRegistry() {
		return registry;
	}

	public void setRegistry(String registry) {
		if (registry != null) {
			this.registry = registry;
			;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPis() {
		return pis;
	}

	public void setPis(String pis) {
		if (pis != null) {
			this.pis = pis;
		}
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		if (cpf != null) {
			this.cpf = cpf;
		}
	}

	@Override
	public String toString() {
		return "[registry=" + registry + ", name=" + name + ", pis=" + pis + ", cpf=" + cpf + "]";
	}

}
