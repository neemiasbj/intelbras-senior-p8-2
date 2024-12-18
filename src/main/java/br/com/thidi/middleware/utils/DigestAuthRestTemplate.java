package br.com.thidi.middleware.utils;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.thidi.middleware.configurations.RestTemplateConfig;
import br.com.thidi.middleware.resource.CLogger;

public class DigestAuthRestTemplate {

	private final RestTemplate restTemplate = RestTemplateConfig.getRestTemplate();
	private final String username;
	private final String password;

	public DigestAuthRestTemplate(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public <T> ResponseEntity<String> executeWithDigestAuth(String url, HttpMethod method, HttpEntity<T> entity) {
		try {
			CLogger.logIntelbrasDebug("Execute initial request",
					"Entity: " + (entity == null ? "null" : entity.toString()));
			ResponseEntity<String> initialResponse = restTemplate.exchange(url, method, entity, String.class);
			if (initialResponse.getStatusCode().is2xxSuccessful()) {
				return initialResponse;
			} else
				throw new Exception(initialResponse.getBody().toString());
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode().value() == 401) {
				String wwwAuthenticateHeader = e.getResponseHeaders().getFirst("WWW-Authenticate");

				if (wwwAuthenticateHeader != null) {
					DigestParams digestParams = parseDigestParams(wwwAuthenticateHeader);
					String digestAuthHeader = calculateDigestAuthHeader(digestParams, url, method.name());

					HttpHeaders newHeaders = new HttpHeaders();
					if (entity == null) {
						newHeaders.set("Authorization", digestAuthHeader);
						HttpEntity<T> updatedEntity = new HttpEntity<>(null, newHeaders);
						CLogger.logIntelbrasDebug("Execute auth request",
								"Entity: " + (updatedEntity == null ? "null" : updatedEntity.toString()));
						return restTemplate.exchange(url, method, updatedEntity, String.class);
					} else {
						HttpHeaders existingHeaders = entity.getHeaders();

						if (existingHeaders == null || existingHeaders.isEmpty())
							newHeaders.set("Authorization", digestAuthHeader);
						else {
							newHeaders.putAll(existingHeaders);
							newHeaders.set("Authorization", digestAuthHeader);
						}

						HttpEntity<T> updatedEntity = new HttpEntity<>(entity.getBody(), newHeaders);

						CLogger.logIntelbrasDebug("Execute auth request",
								"Entity: " + (updatedEntity == null ? "null" : updatedEntity.toString()));

						return restTemplate.exchange(url, method, updatedEntity, String.class);
					}
				}
			} else {
				CLogger.logIntelbrasError("Execute initial request", ("Error during request: " + e.getMessage()));
				e.printStackTrace();
			}
		} catch (Exception ex) {
			CLogger.logIntelbrasError("Execute initial request", ex.getMessage());
			ex.printStackTrace();
		}

		return null;
	}

	private DigestParams parseDigestParams(String wwwAuthenticateHeader) {
		DigestParams params = new DigestParams();

		Pattern pattern = Pattern.compile("(\\w+)=\"([^\"]+)\"");
		Matcher matcher = pattern.matcher(wwwAuthenticateHeader);

		while (matcher.find()) {
			String key = matcher.group(1);
			String value = matcher.group(2);

			switch (key) {
			case "realm":
				params.realm = value;
				break;
			case "nonce":
				params.nonce = value;
				break;
			case "qop":
				params.qop = value;
				break;
			case "opaque":
				params.opaque = value;
				break;
			}
		}
		return params;
	}

	private String calculateDigestAuthHeader(DigestParams params, String uri, String method) {
		String nc = "00000001";
		String cnonce = generateCnonce();

		String response = calculateDigest(username, params.realm, password, method, uri, params.nonce, nc, cnonce,
				params.qop);

		return String.format(
				"Digest username=\"%s\", realm=\"%s\", nonce=\"%s\", uri=\"%s\", qop=%s, nc=%s, cnonce=\"%s\", response=\"%s\", opaque=\"%s\"",
				username, params.realm, params.nonce, uri, params.qop, nc, cnonce, response, params.opaque);
	}

	private static class DigestParams {
		String realm;
		String nonce;
		String qop;
		String opaque;
	}

	public static String calculateDigest(String username, String realm, String password, String method, String uri,
			String nonce, String nc, String cnonce, String qop) {
		String A1 = username + ":" + realm + ":" + password;
		String ha1 = DigestUtils.md5Hex(A1);

		String A2 = method + ":" + uri;
		String ha2 = DigestUtils.md5Hex(A2);

		return DigestUtils.md5Hex(ha1 + ":" + nonce + ":" + nc + ":" + cnonce + ":" + qop + ":" + ha2);
	}

	public static String generateCnonce() {
		return UUID.randomUUID().toString();
	}
}
