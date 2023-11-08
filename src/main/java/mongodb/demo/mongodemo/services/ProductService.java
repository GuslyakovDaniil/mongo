package mongodb.demo.mongodemo.services;

import mongodb.demo.mongodemo.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);
    Product getProduct(String id);
    List<Product> getAllProducts();
    void deleteProduct(String id);

    void saveAllProducts(List<Product> products);
}
