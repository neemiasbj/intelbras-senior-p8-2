package br.com.domain.intelbras.model.api;

import br.com.domain.intelbras.abstraction.DeviceAbstract;
import jakarta.validation.constraints.NotBlank;

public class EmployeerModel extends DeviceAbstract {
	protected EmployeerModel(@NotBlank(message = "Device IP address cannot be blank") String deviceIpAddress) {
		super(deviceIpAddress);
		// TODO Auto-generated constructor stub
	}

	public enum DocTypeEnum {
		CPF, CNPJ
	}

	private DocTypeEnum docType;
	private String doc;
	private String cei;
	private String name;
	private String address;
	private String cnoCaepf;

	public DocTypeEnum getDocType() {
		return docType;
	}

	public void setDocType(DocTypeEnum docType) {
		this.docType = docType;
	}

	public String getDoc() {
		return doc;
	}

	public void setDoc(String doc) {
		this.doc = doc;
	}

	public String getCei() {
		return cei;
	}

	public void setCei(String cei) {
		this.cei = cei;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCnoCaepf() {
		return cnoCaepf;
	}

	public void setCnoCaepf(String cnoCaepf) {
		this.cnoCaepf = cnoCaepf;
	}

	@Override
	public String toString() {
		return String.format("IntelbrasEmployeerModel [docType=%s, doc=%s, cei=%s, name=%s, address=%s, cnoCaepf=%s]",
				this.docType, this.doc, this.cei, this.name, this.address, this.cnoCaepf);
	}

}
