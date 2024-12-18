package br.com.thidi.middleware.utils;

import java.util.Properties;

public class PropertiesUtilImpl {

    private static UtilProperties loader = new UtilProperties();

    public static String getValor(String chave) {
        loader.reloadProperties(); 
        return loader.getValor(chave);
    }

    public static Properties getProperties() {
        loader.reloadProperties();  
        return loader.getProperties();
    }
}
