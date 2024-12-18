package br.com.thidi.middleware.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.PropertyPlaceholderHelper;
import org.springframework.util.SystemPropertyUtils;

public class UtilProperties {

	private static final Logger logger = LogManager.getLogger(UtilProperties.class);
	private Properties props;
	private final String propertiesFileName = "application.properties";
	private final PropertyPlaceholderHelper placeholderHelper;

	protected UtilProperties() {
		props = new Properties();
		placeholderHelper = new PropertyPlaceholderHelper("${", "}", ":", true);
		loadProperties();
	}

	private void loadProperties() {
		InputStream reader = null;

		try {
			reader = new FileInputStream(propertiesFileName);
			// logger.debug("Loaded properties from direct path: " + propertiesFileName);
		} catch (FileNotFoundException e) {
			try {
				reader = new FileInputStream(
						new File(getClass().getClassLoader().getResource(propertiesFileName).getFile()));
				// logger.debug("Loaded properties from classpath.");
			} catch (FileNotFoundException e2) {
				try {
					File prop = new File(
							this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
					reader = new FileInputStream(
							prop.getPath().substring(0, prop.getPath().length() - prop.getName().length())
									+ propertiesFileName);
					// logger.debug("Loaded properties from protection domain path.");
				} catch (FileNotFoundException | URISyntaxException e1) {
					logger.error("Failed to load properties file", e1);
				}
			}
		}

		try {
			if (reader != null) {
				props.clear();
				props.load(reader);
				reader.close();
				// logger.debug("Properties loaded successfully.");
				resolvePlaceholders();
			}
		} catch (IOException e) {
			logger.error("Error loading properties file", e);
		}
	}

	private void resolvePlaceholders() {
		Map<String, String> envMap = System.getenv();
		Properties resolvedProps = new Properties();
		props.forEach((key, value) -> {
			String resolvedValue = placeholderHelper.replacePlaceholders((String) value, props);
			resolvedValue = SystemPropertyUtils.resolvePlaceholders(resolvedValue);
			String envKey = extractEnvKey((String) value);
			if (envKey != null && envMap.containsKey(envKey)) {
				resolvedValue = envMap.get(envKey);
			}
			resolvedProps.put(key, resolvedValue);
		});
		props = resolvedProps;
	}

	private String extractEnvKey(String value) {
		if (value != null && value.contains("${")) {
			int start = value.indexOf("${") + 2;
			int end = value.indexOf(":", start);
			if (end == -1) {
				end = value.indexOf("}", start);
			}
			if (start < end) {
				return value.substring(start, end);
			}
		}
		return null;
	}

	protected void reloadProperties() {
		loadProperties();
	}

	protected String getValor(String chave) {
		return props.getProperty(chave);
	}

	protected Properties getProperties() {
		return props;
	}
}