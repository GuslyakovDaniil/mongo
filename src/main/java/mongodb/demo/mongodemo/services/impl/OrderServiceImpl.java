package mongodb.demo.mongodemo.services.impl;

import mongodb.demo.mongodemo.models.Order;
import mongodb.demo.mongodemo.repos.OrderRepository;
import mongodb.demo.mongodemo.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order getOrder(String id) {
        return orderRepository.findById(id).orElse(null); // Обработку ошибок нужно добавить
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public void deleteOrder(String id) {
        orderRepository.deleteById(id);
    }

    @Override
    public void createOrders(List<Order> orders) {
        orderRepository.saveAll(orders);
    }
    @Override
    public List<Order> getOrdersWithProduct(String productId) {
        return orderRepository.findByProductIdsContaining(productId);
    }


}
