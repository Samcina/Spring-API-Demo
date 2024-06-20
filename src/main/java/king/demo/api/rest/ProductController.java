package king.demo.api.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import king.demo.api.service.ProductService;
import king.demo.api.domain.Product;
import king.demo.api.domain.ProductDTO;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("")
	public List<ProductDTO> listProducts() {
		List<Product> products = productService.listAll();
		List<ProductDTO> productsProjection = new ArrayList<ProductDTO>();
		products.forEach((e) -> productsProjection.add(e.getProjection()));
		return productsProjection;
	}
	
	@GetMapping("/{id}")
	public Product getProductById(@PathVariable Long id) {
		return productService.findById(id);
	}
	
	@GetMapping("/search")
	public List<ProductDTO> searchProductsByName(@RequestParam(name = "q") String query) {
		List<Product> products = productService.searchByName(query);
		List<ProductDTO> productsProjection = new ArrayList<ProductDTO>();
		products.forEach((e) -> productsProjection.add(e.getProjection()));
		return productsProjection;
	}
	
	@GetMapping("/filter")
	public List<ProductDTO> filterProducts(@RequestParam(name = "category", required = false) String category, @RequestParam(name = "pricegt", required = false) Float priceGreaterThan, @RequestParam(name = "pricelt", required = false) Float priceLessThan) {
		List<Product> products = productService.listAll();
		if(Objects.nonNull(category)) {
			products = products.stream().filter((p) -> {return p.getCategory().equalsIgnoreCase(category);}).collect(Collectors.toList());
		}
		if(Objects.nonNull(priceGreaterThan)) {
			products = products.stream().filter((p) -> {return p.getPrice().compareTo(priceGreaterThan) >= 0;}).collect(Collectors.toList());
		}
		if(Objects.nonNull(priceLessThan)) {
			products = products.stream().filter((p) -> {return p.getPrice().compareTo(priceLessThan) <= 0;}).collect(Collectors.toList());
		}
		List<ProductDTO> productsProjection = new ArrayList<ProductDTO>();
		products.forEach((e) -> productsProjection.add(e.getProjection()));
		
		return productsProjection;
	}
}
