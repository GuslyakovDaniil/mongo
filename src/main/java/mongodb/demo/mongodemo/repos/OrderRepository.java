package mongodb.demo.mongodemo.repos;

import mongodb.demo.mongodemo.models.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findByProductIdsContaining(String productId);
}
