package br.com.thidi.middleware.configurations;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import br.com.thidi.middleware.utils.UtilPropertiesImpl;

public class RestTemplateConfiguration {
	private static Integer timeWaitingRestTemplate = Integer.valueOf(UtilPropertiesImpl.getValor("time.waiting.resttemplate.seconds"));

	public static RestTemplate createCustomRestTemplate() {
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		clientHttpRequestFactory.setConnectTimeout(timeWaitingRestTemplate);

		return new RestTemplate(clientHttpRequestFactory);
	}

}