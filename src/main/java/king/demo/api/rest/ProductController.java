package king.demo.api.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import king.demo.api.domain.Product;
import king.demo.api.domain.ProductDTO;
import king.demo.api.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("")
	public List<ProductDTO> listProducts() {
		return Product.getProjectionFromList(productService.listAll());
	}
	
	@GetMapping("/{id}")
	public Product getProductById(@PathVariable Long id) {
		return productService.findById(id);
	}
	
	@GetMapping("/search")
	public List<ProductDTO> searchProductsByName(@RequestParam(name = "q") String query) {
		return Product.getProjectionFromList(productService.searchByName(query));
	}
	
	@GetMapping("/filter")
	public List<ProductDTO> filterProducts(@RequestParam(name = "category", required = false) String category, @RequestParam(name = "pricegt", required = false) Float priceGreaterThan, @RequestParam(name = "pricelt", required = false) Float priceLessThan) {
		return Product.getProjectionFromList(productService.filterByCategoryAndPrice(category, priceGreaterThan, priceLessThan));
	}
}
