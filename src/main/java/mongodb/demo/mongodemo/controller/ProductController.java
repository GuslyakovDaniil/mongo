package mongodb.demo.mongodemo.controller;

import mongodb.demo.mongodemo.models.Order;
import mongodb.demo.mongodemo.models.Product;
import mongodb.demo.mongodemo.services.OrderService;
import mongodb.demo.mongodemo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable String id) {
        return productService.getProduct(id);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable String id, @RequestBody Product updatedProduct) {
        Product existingProduct = productService.getProduct(id);

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());

        return productService.createProduct(existingProduct);
    }

    @GetMapping("/{id}/orders")
    public List<Order> getOrdersWithProduct(@PathVariable String id) {
        Product product = productService.getProduct(id);

        if (product != null) {
            List<Order> ordersWithProduct = orderService.getOrdersWithProduct(id);
            return ordersWithProduct;
        } else {
            return Collections.emptyList();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
    }
}
