package mongodb.demo.mongodemo.models;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class OrderFactory {
    private static AtomicInteger nextId = new AtomicInteger(1); // Статическая переменная для отслеживания id
    private final Faker faker = new Faker();

    public Order make(String buyerId, List<String> productIds) {
        final Order result = new Order(buyerId, productIds);
        result.setId(Integer.toString(nextId.getAndAdd(1))); // Увеличиваем id и устанавливаем его в заказ
        return result;
    }

    public List<Order> orders(int min, int max, List<Buyer> buyers, List<Product> availableProducts) {
        return IntStream.range(0, faker.number().numberBetween(min, max))
                .mapToObj(i -> makeOrderWithRandomProducts(buyers, availableProducts))
                .collect(Collectors.toList());
    }

    private Order makeOrderWithRandomProducts(List<Buyer> buyers, List<Product> availableProducts) {
        Buyer randomBuyer = buyers.get(faker.random().nextInt(buyers.size()));

        List<String> orderProductIds = new ArrayList<>();
        int numProducts = faker.number().numberBetween(1, 11);

        for (int i = 0; i < numProducts; i++) {
            String randomProductId = getRandomProductId(availableProducts, orderProductIds);
            orderProductIds.add(randomProductId);
        }

        return new Order(randomBuyer.getId(), orderProductIds);
    }

    private String getRandomProductId(List<Product> availableProducts, List<String> orderProductIds) {
        List<Product> remainingProducts = availableProducts.stream()
                .filter(product -> !orderProductIds.contains(product.getId()))
                .collect(Collectors.toList());

        if (remainingProducts.isEmpty()) {
            return availableProducts.get(faker.random().nextInt(availableProducts.size())).getId();
        } else {
            return remainingProducts.get(faker.random().nextInt(remainingProducts.size())).getId();
        }
    }
}
