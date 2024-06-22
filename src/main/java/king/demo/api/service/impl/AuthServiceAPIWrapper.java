package king.demo.api.service.impl;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import king.demo.api.domain.AuthUser;
import king.demo.api.service.AuthService;

@Service
public class AuthServiceAPIWrapper implements AuthService {

	private RestTemplate restTemplate; // Spring's HTTP client
	private String apiUrl = "https://dummyjson.com/auth";

	public AuthServiceAPIWrapper() {
		this.restTemplate = new RestTemplate();
	}
	
	//logs in user and fetches bearer token from DummyJSON API
	@Override
	public AuthUser loginUser(String username, String password) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode rootNode = mapper.createObjectNode();
		rootNode.put("username", username);
		rootNode.put("password", password);


		HttpEntity<String> request = null;
		try {
			request = new HttpEntity<String>(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode), headers);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		ResponseEntity<AuthUser> response = restTemplate.postForEntity( apiUrl + "/login", request , AuthUser.class );
		if (response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		}
		return null;
	}

	//checks user role based on bearer token from DummyJSON API
	@Override
	public String getCurrentAuthUser(String bearerToken) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", bearerToken);

		HttpEntity<String> request = new HttpEntity<String>(headers);

		ResponseEntity<String> response = restTemplate.exchange( apiUrl + "/me", HttpMethod.GET, request , String.class );
		if (response.getStatusCode() == HttpStatus.OK) {
			ObjectMapper mapper = new ObjectMapper();		
			try {
				JsonNode jsonNode = mapper.readTree(response.getBody());
				return mapper.convertValue(jsonNode.get("role"), String.class);
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
