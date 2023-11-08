package mongodb.demo.mongodemo.services.impl;

import mongodb.demo.mongodemo.models.Order;
import mongodb.demo.mongodemo.models.Product;
import mongodb.demo.mongodemo.repos.OrderRepository;
import mongodb.demo.mongodemo.repos.ProductRepository;
import mongodb.demo.mongodemo.services.ProductService;
import mongodb.demo.mongodemo.utils.ClientErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, OrderRepository orderRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getProduct(String id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void deleteProduct(String id) {
        Product product = getProduct(id);

        if (product != null) {
            List<Order> ordersContainingProduct = orderRepository.findByProductIdsContaining(id);

            for (Order order : ordersContainingProduct) {
                order.getProductIds().remove(id);
                orderRepository.save(order);
            }

            productRepository.deleteById(id);
        }
    }


    @Override
    public void saveAllProducts(List<Product> products) {
        productRepository.saveAll(products);
    }
}

