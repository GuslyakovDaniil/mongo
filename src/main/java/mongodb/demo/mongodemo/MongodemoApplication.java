package mongodb.demo.mongodemo;

import mongodb.demo.mongodemo.models.*;
import mongodb.demo.mongodemo.services.BuyersService;
import mongodb.demo.mongodemo.services.OrderService;
import mongodb.demo.mongodemo.services.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class MongodemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongodemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(
            BuyersService buyersService, BuyerFactory buyerFactory,
            ProductFactory productFactory, ProductService productService,
            OrderService orderService, OrderFactory orderFactory) {
        return args -> {
            // Создание и сохранение объектов Buyer
            List<Buyer> buyers = buyerFactory.listBuilder().buyers(100, 100);
            buyersService.saveAllBuyers(buyers);

            Buyer newBuyer = buyerFactory.make(BuyerFactory.oneUpId);
            buyersService.createBuyer(newBuyer);
            System.out.println("Created buyer: " + newBuyer);

            String buyerId = newBuyer.getId();
            Buyer retrievedBuyer = buyersService.getBuyer(buyerId);
            System.out.println("Retrieved buyer by ID: " + retrievedBuyer);

            // Создание и сохранение объектов Product
            List<Product> products = productFactory.listBuilder().products(50, 50);
            productService.saveAllProducts(products);

            Product newProduct = productFactory.make();
            productService.createProduct(newProduct);
            System.out.println("Created product: " + newProduct);

            String productId = newProduct.getId();
            Product retrievedProduct = productService.getProduct(productId);
            System.out.println("Retrieved product by ID: " + retrievedProduct);

            // Создание и сохранение объектов Order
            List<Order> orders = orderFactory.orders(10, 20, buyers, products);
            orderService.createOrders(orders);

            // Вычисление средней цены продуктов
            List<Product> allProducts = productService.getAllProducts();
            double averagePrice = allProducts.stream().mapToDouble(Product::getPrice).average().orElse(0.0);
            System.out.println("Average product price: " + averagePrice);

            // Вывод информации о созданных покупателях, продуктах и заказах
            System.out.println("All buyers:");
            List<Buyer> allBuyers = buyersService.getAllBuyers();
            allBuyers.forEach(System.out::println);

            System.out.println("All products:");
            allProducts.forEach(System.out::println);

            System.out.println("All orders:");
            List<Order> allOrders = orderService.getAllOrders();
            allOrders.forEach(System.out::println);
        };
    }



}

