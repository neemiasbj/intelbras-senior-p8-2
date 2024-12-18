
package br.com.thidi.middleware.utils;


 import java.io.File;
 import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.IOException;
 import java.io.InputStream;
 import java.util.Map;
 import java.util.Properties;

 import org.apache.logging.log4j.LogManager;
 import org.apache.logging.log4j.Logger;
 import org.springframework.util.PropertyPlaceholderHelper;
 import org.springframework.util.SystemPropertyUtils;




 public class MiddlewareUtilProperties
 {
	 private static final Logger logger = LogManager.getLogger(MiddlewareUtilProperties.class);
	 private Properties props;
	 private final String propertiesFileName = "application.properties";
	 private final PropertyPlaceholderHelper placeholderHelper;

	
	 protected MiddlewareUtilProperties() {
		 this.props = new Properties();
		
		 this.placeholderHelper = new PropertyPlaceholderHelper("${", "}", ":", true);
		 loadProperties();
		 }

	
	 private void loadProperties() {
		 InputStream reader = null;
		
		 try {
			 reader = new FileInputStream("application.properties");
			 }
		 catch (FileNotFoundException e) {
			 try {
				 reader = new FileInputStream(
						new File(getClass().getClassLoader().getResource("application.properties").getFile()));
				 }
			 catch (FileNotFoundException e2) {
				 try {
					 File prop = new File(
							getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
					 reader = new FileInputStream(String
							.valueOf(prop.getPath().substring(0, prop.getPath().length() - prop.getName().length()))
							+ "application.properties");
					 }
				 catch (FileNotFoundException | java.net.URISyntaxException e1) {
					 logger.error("Failed to load properties file", e1);
					 }
				 }
			 }
		
		 try {
			 if (reader != null) {
				 this.props.clear();
				 this.props.load(reader);
				 reader.close();
				
				 resolvePlaceholders();
				 }
			 } catch (IOException e) {
			 logger.error("Error loading properties file", e);
			 }
		 }

	
	 private void resolvePlaceholders() {
		 Map<String, String> envMap = System.getenv();
		 Properties resolvedProps = new Properties();
		 this.props.forEach((key, value) -> {
			 String resolvedValue = this.placeholderHelper.replacePlaceholders((String) value, this.props);
			 resolvedValue = SystemPropertyUtils.resolvePlaceholders(resolvedValue);
			 String envKey = extractEnvKey((String) value);
			 if (envKey != null && envMap.containsKey(envKey)) {
				 resolvedValue = (String) envMap.get(envKey);
				 }
			 resolvedProps.put(key, resolvedValue);
			 });
		 this.props = resolvedProps;
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
		 return this.props.getProperty(chave);
		 }

	
	 protected Properties getProperties() {
		 return this.props;
		 }
	 }

