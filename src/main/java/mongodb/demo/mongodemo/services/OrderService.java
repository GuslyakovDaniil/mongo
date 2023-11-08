package mongodb.demo.mongodemo.services;

import mongodb.demo.mongodemo.models.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(Order order);
    void createOrders(List<Order> orders);
    Order getOrder(String id);
    List<Order> getAllOrders();
    void deleteOrder(String id);

    List<Order> getOrdersWithProduct(String productId); // Добавьте этот метод
}

