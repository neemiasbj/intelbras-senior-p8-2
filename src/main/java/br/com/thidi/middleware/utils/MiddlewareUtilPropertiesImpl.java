package br.com.thidi.middleware.utils;

import br.com.thidi.middleware.resource.CLogger;
import java.util.Properties;

public class MiddlewareUtilPropertiesImpl {
	private static MiddlewareUtilProperties loader = new MiddlewareUtilProperties();

	public static String getValor(String chave) {
		loader.reloadProperties();
		String valor = loader.getValor(chave);
		if (valor == null || valor.equals(""))
			CLogger.logPropertiesInfo(chave, valor);
		return valor;
	}

	public static Properties getProperties() {
		loader.reloadProperties();
		return loader.getProperties();
	}
}
