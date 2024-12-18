package br.com.thidi.middleware.utils;

import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import com.google.gson.Gson;

public class UtilPropertiesValid {

	private String prop;
	private String value;

	public UtilPropertiesValid(String prop, String value) {
		this.prop = prop;
		this.value = value;
	}

	@Override
	public String toString() {
		return "UtilPropertiesValid [prop:" + prop + ", value:" + value + "]";
	}

	public String getProp() {
		return prop;
	}

	public void setProp(String prop) {
		this.prop = prop;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static String personalizeMessageAsJSON() {
        Gson gson = new Gson();
        Properties prop = PropertiesUtilImpl.getProperties(); // Recarregar propriedades

        List<UtilPropertiesValid> propriedadesList = prop.entrySet().stream()
                .map(entry -> new UtilPropertiesValid(entry.getKey().toString(), entry.getValue().toString()))
                .collect(Collectors.toList());
        return gson.toJson(propriedadesList);
    }

}
