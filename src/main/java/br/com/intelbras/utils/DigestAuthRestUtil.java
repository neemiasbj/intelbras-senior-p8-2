package br.com.intelbras.utils;

import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.thidi.middleware.configurations.RestTemplateConfig;
import br.com.thidi.middleware.resource.CLogger;

public class DigestAuthRestUtil {
	private final RestTemplate restTemplate = RestTemplateConfig.getRestTemplate();
	ObjectMapper objectMapper = new ObjectMapper();
	private final String username;
	private final String password;

	public DigestAuthRestUtil(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public <T> ResponseEntity<String> executeWithDigestAuth(String url, HttpMethod method, HttpEntity<T> entity)
			throws Exception {
		try {
			ResponseEntity<String> initialResponse = this.restTemplate.exchange(url, method, entity, String.class,
					new Object[0]);
			if (initialResponse.getStatusCode().is2xxSuccessful())
				return initialResponse;
//			throw new Exception(initialResponse.toString());
		} catch (HttpClientErrorException ex) {
			if (ex.getStatusCode().value() == 401) {
				String wwwAuthenticateHeader = ex.getResponseHeaders().getFirst("WWW-Authenticate");

				if (wwwAuthenticateHeader != null) {
					try {
						DigestParams digestParams = parseDigestParams(wwwAuthenticateHeader);
						String digestAuthHeader = calculateDigestAuthHeader(digestParams, url, method.name());

						HttpHeaders newHeaders = new HttpHeaders();
						if (entity == null) {
							newHeaders.set("Authorization", digestAuthHeader);
							HttpEntity<T> httpEntity = new HttpEntity<T>(null,
									(MultiValueMap<String, String>) newHeaders);
							return this.restTemplate.exchange(url, method, httpEntity, String.class, new Object[0]);
						}
						HttpHeaders existingHeaders = entity.getHeaders();

						if (existingHeaders == null || existingHeaders.isEmpty()) {
							newHeaders.set("Authorization", digestAuthHeader);
						} else {
							newHeaders.putAll((MultiValueMap<String, String>) existingHeaders);
							newHeaders.set("Authorization", digestAuthHeader);
						}

						HttpEntity<T> updatedEntity = new HttpEntity<T>(entity.getBody(),
								(MultiValueMap<String, String>) newHeaders);

						return this.restTemplate.exchange(url, method, updatedEntity, String.class, new Object[0]);
					} catch (Exception e1) {
						throw e1;
					}
				}
			} else {
				CLogger.logIntelbrasError("Execute initial request", "Error during request: " + ex.getMessage());
				throw ex;
			}
		} catch (Exception ex) {
			throw ex;
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
			String str1;
			switch ((str1 = key).hashCode()) {
			case -1010695135:
				if (!str1.equals("opaque")) {
					continue;
				}

				params.opaque = value;
			case 112146:
				if (!str1.equals("qop"))
					continue;
				params.qop = value;
			case 105002991:
				if (!str1.equals("nonce"))
					continue;
				params.nonce = value;
			case 108386959:
				if (!str1.equals("realm"))
					continue;
				params.realm = value;
			}
		}
		return params;
	}

	private String calculateDigestAuthHeader(DigestParams params, String uri, String method) {
		String nc = "00000001";
		String cnonce = generateCnonce();

		String response = calculateDigest(this.username, params.realm, this.password, method, uri, params.nonce, nc,
				cnonce, params.qop);

		return String.format(
				"Digest username=\"%s\", realm=\"%s\", nonce=\"%s\", uri=\"%s\", qop=%s, nc=%s, cnonce=\"%s\", response=\"%s\", opaque=\"%s\"",
				new Object[] { this.username, params.realm, params.nonce, uri, params.qop, nc, cnonce, response,
						params.opaque });
	}

	private String calculateDigest(String username, String realm, String password, String method, String uri,
			String nonce, String nc, String cnonce, String qop) {
		String A1 = String.valueOf(username) + ":" + realm + ":" + password;
		String ha1 = DigestUtils.md5Hex(A1);

		String A2 = String.valueOf(method) + ":" + uri;
		String ha2 = DigestUtils.md5Hex(A2);

		return DigestUtils.md5Hex(String.valueOf(ha1) + ":" + nonce + ":" + nc + ":" + cnonce + ":" + qop + ":" + ha2);
	}

	private String generateCnonce() {
		return UUID.randomUUID().toString();
	}

	private static class DigestParams {
		String realm;
		String nonce;
		String qop;
		String opaque;
	}
}
