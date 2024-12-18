import java.io.InputStream;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;

import br.com.thidi.middleware.utils.DigestAuthRestTemplate;

public class RequestTest {

	static ObjectMapper objectMapper = new ObjectMapper();
	static DigestAuthRestTemplate digestAuthRestTemplate = new DigestAuthRestTemplate("admin", "Abc123!@");

	public static void main(String[] args) throws Exception {
		objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.UPPER_CAMEL_CASE);
		sendFaceData();
		System.out.println("SENT");
	}

	private static void setUserPhoto() throws Exception {
		URL url = new URL(
				"https://lh3.googleusercontent.com/pw/AP1GczNmg2Z9V4sMHwSCCWNkJzGl9UFAjbreyB-YtVOs52K0mbKLbI5M8BrjQ1TaZcShmPbH3ratP1fr1ohir0nPtuEUJeJL-SPsUIZs_jgXmgodktowcJKJCIndWjj46lnaKVLloNvIA8ckbBs3OTz0e6ePyw=w1620-h911-s-no-gm?authuser=0");
		InputStream imageInputStream = url.openStream();

		byte[] imageBytes = IOUtils.toByteArray(imageInputStream);

		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
//		IncludeUserFaceRequest faceDataRequest = new IncludeUserFaceRequest("5");
//		body.add("FaceDataRecord", faceDataRequest);

		Resource imageResource = new ByteArrayResource(imageBytes) {
			@Override
			public String getFilename() {
				return "image.jpg";
			}
		};
		body.add("FaceImage", imageResource);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
		digestAuthRestTemplate.executeWithDigestAuth(
				"http://192.168.0.80/ISAPI/Intelligent/FDLib/FaceDataRecord?format=json", HttpMethod.POST,
				requestEntity);
	}

	public static void sendFaceData() {
		try {
			// Convert image to Base64
			URL url = new URL(
					"https://lh3.googleusercontent.com/pw/AP1GczNmg2Z9V4sMHwSCCWNkJzGl9UFAjbreyB-YtVOs52K0mbKLbI5M8BrjQ1TaZcShmPbH3ratP1fr1ohir0nPtuEUJeJL-SPsUIZs_jgXmgodktowcJKJCIndWjj46lnaKVLloNvIA8ckbBs3OTz0e6ePyw=w1620-h911-s-no-gm?authuser=0");
			InputStream imageInputStream = url.openStream();
			byte[] imageBytes = IOUtils.toByteArray(imageInputStream);
			String encodedImage = Base64.getEncoder().encodeToString(imageBytes);

			// Prepare JSON data
			Map<String, String> faceDataRecord = new HashMap<>();
			faceDataRecord.put("faceLibType", "blackFD");
			faceDataRecord.put("FDID", "1");
			faceDataRecord.put("FPID", "4");

			String jsonPayload = objectMapper.writeValueAsString(faceDataRecord);

			// Prepare image resource as byte array
			Resource imageResource = new ByteArrayResource(imageBytes) {
				@Override
				public String getFilename() {
					return "image.jpg";
				}
			};

			// Create headers and set content type to form-data
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.MULTIPART_FORM_DATA);

			// Prepare form-data
			MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
			body.add("FaceDataRecord", jsonPayload);
			body.add("FaceImage", imageResource);

			// Create HttpEntity
			HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

			// Send POST request
			digestAuthRestTemplate.executeWithDigestAuth(
					"http://192.168.0.80/ISAPI/Intelligent/FDLib/FaceDataRecord?format=json", HttpMethod.POST,
					requestEntity);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void excludeAllUsers() throws Exception {
//		DeleteUsers.UserInfoDelCondDetails userInfoDelCondDetails = new DeleteUsers.UserInfoDelCondDetails();
//		DeleteUsers userInfoDelCond = new DeleteUsers(userInfoDelCondDetails);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

//		HttpEntity<String> entity = new HttpEntity<String>(objectMapper.writeValueAsString(userInfoDelCond), headers);
//		System.out.println("Entity: " + entity.toString());
//		ResponseEntity<String> response = digestAuthRestTemplate.executeWithDigestAuth("http://177.81.228.104:9090/ISAPI/AccessControl/UserInfo/Delete?format=json", HttpMethod.PUT, entity);

	}

}
