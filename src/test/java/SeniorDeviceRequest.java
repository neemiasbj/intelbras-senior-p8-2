import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class SeniorDeviceRequest {

	public static void main(String[] args) throws Exception{
		String urlDevice = "https://sam-api.senior.com.br/sdk/v1/device";
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("partner_key", "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MjIsIm1hbnVmYWN0dXJlciI6IkhJS1ZJU0lPTiJ9.hkNnTcOgx4urNmoVJYrUTfucutOp7THwTlUg0xeG1X0");
		headers.add("driver_key", "eyJhbGciOiJIUzI1NiJ9.eyJkcml2ZXJJZCI6MiwidGVuYW50IjoicmRtc3MtZGV2Y29tYnIifQ.yKhqd8l6Bopch2AsC4vEsnK9nzTe3qFc9Zwji5I9agw");
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<String> devicess = restTemplate.exchange(urlDevice, HttpMethod.GET, entity, String.class);
		System.out.println(devicess.getBody());
//		ResponseEntity<List<ManagerDevice>> response = restTemplate.exchange(urlDevice, HttpMethod.GET, entity, new ParameterizedTypeReference<List<ManagerDevice>>() {
//		});

//		System.out.println(response.getBody());
	}
}
