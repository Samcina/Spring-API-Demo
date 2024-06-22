package king.demo.api.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import king.demo.api.domain.Product;
import king.demo.api.domain.ProductDTO;
import king.demo.api.service.AuthService;
import king.demo.api.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private AuthService authService;
	
	//lists all products
	@GetMapping("")
	public List<ProductDTO> listProducts() {
		return Product.getProjectionFromList(productService.listAll());
	}
	
	//lists single product based on id
	@GetMapping("/{id}")
	public Product getProductById(@PathVariable Long id) {
		return productService.findById(id);
	}
	
	//lists all products based on search query
	@GetMapping("/search")
	public List<ProductDTO> searchProductsByName(@RequestParam(name = "q") String query) {
		return Product.getProjectionFromList(productService.searchByName(query));
	}
	
	//lists all products based on category and price
	@GetMapping("/filter")
	public List<ProductDTO> filterProducts(@RequestParam(name = "category", required = false) String category, @RequestParam(name = "pricegt", required = false) Float priceGreaterThan, @RequestParam(name = "pricelt", required = false) Float priceLessThan) {
		return Product.getProjectionFromList(productService.filterByCategoryAndPrice(category, priceGreaterThan, priceLessThan));
	}
	
	//lists all products only if the user is logged in
	@GetMapping("/auth/user")
	public ResponseEntity<List<ProductDTO>> listProductsWithUserAuth(@RequestHeader(value = "Authorization", required = false) String bearerToken) {
		if(bearerToken == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		String role = authService.getCurrentAuthUser(bearerToken);
		if(!(role.equals("user") || role.equals("admin"))) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		return ResponseEntity.ok(Product.getProjectionFromList(productService.listAll()));
	}
	
	//lists all products only if an admin is logged in
	@GetMapping("/auth/admin")
	public ResponseEntity<List<ProductDTO>> listProductsWithAdminAuth(@RequestHeader(value = "Authorization", required = false) String bearerToken) {
		if(bearerToken == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		String role = authService.getCurrentAuthUser(bearerToken);
		if(!(role.equals("admin"))) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		return ResponseEntity.ok(Product.getProjectionFromList(productService.listAll()));
	}
}
