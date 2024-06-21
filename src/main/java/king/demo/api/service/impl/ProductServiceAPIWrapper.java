package king.demo.api.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import king.demo.api.domain.Product;
import king.demo.api.service.ProductService;

@Service
public class ProductServiceAPIWrapper implements ProductService {

	private RestTemplate restTemplate; // Spring's HTTP client
	private String apiUrl = "https://dummyjson.com/products";

	public ProductServiceAPIWrapper() {
		this.restTemplate = new RestTemplate();
	}

	@Override
	public List<Product> listAll() {
		ResponseEntity<String> response = restTemplate.getForEntity(apiUrl + "?limit=0", String.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			ObjectMapper mapper = new ObjectMapper();		
			try {
				JsonNode jsonNode = mapper.readTree(response.getBody());
				return mapper.convertValue(jsonNode.get("products"), new TypeReference<List<Product>>(){});
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return null;
		} else {
			return null;
		}
	}

	@Override
	public Product findById(Long id) {
		ResponseEntity<String> response = restTemplate.getForEntity(apiUrl + "/" + id.toString(), String.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			ObjectMapper mapper = new ObjectMapper();	
			try {
				return mapper.readValue(response.getBody(), Product.class);
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

			return null;
		} else {
			return null;
		}
	}

	@Override
	public List<Product> searchByName(String query) {
		ResponseEntity<String> response = restTemplate.getForEntity(apiUrl + "/search?q=" + query, String.class);
		if (response.getStatusCode() == HttpStatus.OK) {
			ObjectMapper mapper = new ObjectMapper();		
			try {
				JsonNode jsonNode = mapper.readTree(response.getBody());
				return mapper.convertValue(jsonNode.get("products"), new TypeReference<List<Product>>(){});
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return null;
		} else {
			return null;
		}
	}

	@Override
	public List<Product> filterByCategoryAndPrice(String category, Float priceGreaterThan, Float priceLessThan) {
		List<Product> products = listAll();
		if(Objects.nonNull(category)) {
			products = products.stream().filter((p) -> {return p.getCategory().equalsIgnoreCase(category);}).collect(Collectors.toList());
		}
		if(Objects.nonNull(priceGreaterThan)) {
			products = products.stream().filter((p) -> {return p.getPrice().compareTo(priceGreaterThan) >= 0;}).collect(Collectors.toList());
		}
		if(Objects.nonNull(priceLessThan)) {
			products = products.stream().filter((p) -> {return p.getPrice().compareTo(priceLessThan) <= 0;}).collect(Collectors.toList());
		}
		return products;
	}
	
	
}
