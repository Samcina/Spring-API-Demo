package king.demo.api.service;

import java.util.List;

import king.demo.api.domain.Product;

public interface ProductService {
	List<Product> listAll();
	Product findById(Long id);
	List<Product> searchByName(String query);
}
