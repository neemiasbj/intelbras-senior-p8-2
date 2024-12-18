package br.com.domain.intelbras.model.api;

import br.com.util.Utils;

public class BiometryModel {

	private String registry;
	private String mainBiometry;
	private String alternativeBiometry;

	public String getRegistry() {
		return this.registry;
	}

	public String getMainBiometry() {
		return this.mainBiometry;
	}

	public String getAlternativeBiometry() {
		return this.alternativeBiometry;
	}

	public void setRegistry(String registry) {
		this.registry = Utils.padStart(registry, 12, '0');
	}

	public void setMainBiometry(String mainBiometry) {
		this.mainBiometry = mainBiometry;
	}

	public void setAlternativeBiometry(String alternativeBiometry) {
		this.alternativeBiometry = alternativeBiometry;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(" { \n\t");
		if (this.registry != null) {
			builder.append("registry: ");
			builder.append(this.registry);
			builder.append(", \n\t");
		}
		if (this.mainBiometry != null) {
			builder.append("mainBiometry: ");
			builder.append(this.mainBiometry);
			builder.append(", \n\t");
		}
		if (this.alternativeBiometry != null) {
			builder.append("alternativeBiometry: ");
			builder.append(this.alternativeBiometry);
		}
		builder.append(" \n}");
		return builder.toString();
	}

}
