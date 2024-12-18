package br.com.domain.intelbras.model.api;

public class IncludeBiometryModel {

	private String mainBiometry, alternativeBiometry, registry, alternativeFinger, mainFinger;

	public String getMainBiometry() {
		return mainBiometry;
	}

	public void setMainBiometry(String mainBiometry) {
		this.mainBiometry = mainBiometry;
	}

	public String getAlternativeBiometry() {
		return alternativeBiometry;
	}

	public void setAlternativeBiometry(String alternativeBiometry) {
		this.alternativeBiometry = alternativeBiometry;
	}

	public String getRegistry() {
		return registry;
	}

	public void setRegistry(String registry) {
		this.registry = registry;
	}

	public String getAlternativeFinger() {
		return alternativeFinger;
	}

	public void setAlternativeFinger(String alternativeFinger) {
		this.alternativeFinger = alternativeFinger;
	}

	public String getMainFinger() {
		return mainFinger;
	}

	public void setMainFinger(String mainFinger) {
		this.mainFinger = mainFinger;
	}

	@Override
	public String toString() {
		return "IntelbrasIncludeBiometryModel [mainBiometry=" + mainBiometry + ", alternativeBiometry=" + alternativeBiometry + ", registry=" + registry + ", alternativeFinger=" + alternativeFinger + ", mainFinger=" + mainFinger + "]";
	}

}
