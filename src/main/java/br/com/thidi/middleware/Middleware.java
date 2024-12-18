
package br.com.thidi.middleware;

import br.com.thidi.middleware.resource.CLogger;
import br.com.thidi.middleware.services.PropertiesService;
import br.com.thidi.middleware.utils.MiddlewarePropertiesUtilImpl;
import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.Collections;
import java.util.Properties;
import org.slf4j.MDC;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Middleware extends Thread {
	static PropertiesService ps = new PropertiesService();

	public void run() {
		startMiddleware();
	}

	public static void main(String[] args) {
		Middleware c = new Middleware();
		c.run();
	}

	public void startMiddleware() {
		this.setName("THIDI - HIKVISION/SENIOR MIDDLEWARE");
		RuntimeMXBean rt = ManagementFactory.getRuntimeMXBean();
		String pid = rt.getName();
		MDC.put("PID", pid);

		CLogger.logPropertiesInfo("FILE CONFIG DATA", "api.port: " + MiddlewarePropertiesUtilImpl.getValor("api.port"));

		CLogger.logPropertiesInfo("FILE CONFIG DATA",
				"senior.partner_key: " + MiddlewarePropertiesUtilImpl.getValor("senior.partner_key"));

		CLogger.logPropertiesInfo("FILE CONFIG DATA",
				"senior.driver_key: " + MiddlewarePropertiesUtilImpl.getValor("senior.driver_key"));

		CLogger.logPropertiesInfo("FILE CONFIG DATA",
				"senior.api.sdk.uri: " + MiddlewarePropertiesUtilImpl.getValor("senior.api.sdk.uri"));

		CLogger.logPropertiesInfo("FILE CONFIG DATA",
				"senior.api.sdk.timeout: " + MiddlewarePropertiesUtilImpl.getValor("senior.api.sdk.timeout"));

		CLogger.logPropertiesInfo("FILE CONFIG DATA",
				"senior.websocket.uri: " + MiddlewarePropertiesUtilImpl.getValor("senior.websocket.uri"));

		CLogger.logPropertiesInfo("FILE CONFIG DATA", "senior.websocket.reconnect.interval.seconds: "
				+ MiddlewarePropertiesUtilImpl.getValor("senior.websocket.reconnect.interval.seconds"));

		CLogger.logPropertiesInfo("FILE CONFIG DATA", "time.keep.alive.senior.pendency: "
				+ MiddlewarePropertiesUtilImpl.getValor("time.keep.alive.senior.pendency"));

		CLogger.logPropertiesInfo("FILE CONFIG DATA", "time.keep.alive.senior.device: "
				+ MiddlewarePropertiesUtilImpl.getValor("time.keep.alive.senior.device"));

		CLogger.logPropertiesInfo("FILE CONFIG DATA", "time.waiting.resttemplate.seconds: "
				+ MiddlewarePropertiesUtilImpl.getValor("time.waiting.resttemplate.seconds"));

		CLogger.logPropertiesInfo("FILE CONFIG DATA", "webhook.reprocessIntervalInMinutes: "
				+ MiddlewarePropertiesUtilImpl.getValor("webhook.reprocessIntervalInMinutes"));

		SpringApplication app = new SpringApplication(new Class[] { Middleware.class });
		app.setBannerMode(Banner.Mode.OFF);
		app.setDefaultProperties(
				Collections.singletonMap("server.port", MiddlewarePropertiesUtilImpl.getValor("api.port")));

		app.run(new String[0]);

		while (true) {
			try {
				while (true) {
					Thread.sleep(600000);
					CLogger.logGeneralInfo("HIKVISION", "MIDDLEWARE ALIVE");
				}
			} catch (Exception exception) {
			}
		}
	}

	public synchronized String getVersion() {
		String version = null;

		try {
			Properties p = new Properties();
			InputStream is = getClass().getResourceAsStream("/META-INF/maven/pom.properties");
			if (is != null) {
				p.load(is);
				version = p.getProperty("version", "");
			}
		} catch (Exception exception) {
		}

		if (version == null) {
			Package aPackage = getClass().getPackage();
			if (aPackage != null) {
				version = aPackage.getImplementationVersion();
				if (version == null) {
					version = aPackage.getSpecificationVersion();
				}
			}
		}

		if (version == null) {
			version = "";
		}

		return version;
	}
}
