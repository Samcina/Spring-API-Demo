package king.demo.api.rest;

import java.util.ArrayList;
import java.util.List;

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
}
