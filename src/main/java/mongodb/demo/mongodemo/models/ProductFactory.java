package mongodb.demo.mongodemo.models;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Component
public class ProductFactory {
    private static AtomicInteger nextId = new AtomicInteger(1);
    private final Faker faker = new Faker();

    public String name() {
        return faker.commerce().productName();
    }

    public double price() {
        return faker.number().randomDouble(2, 1, 1000);
    }

    public Product make(UnaryOperator<Product>... productModifiers) {
        final Product result = new Product(name(), price());
        Stream.of(productModifiers).forEach(v -> v.apply(result));
        result.setId(UUID.randomUUID().toString());
        return result;
    }


    public ProductListDTOFactory listBuilder() {
        return new ProductListDTOFactory();
    }

    public class ProductListDTOFactory {
        public List<Product> products(int min, int max, UnaryOperator<Product>... productModifiers) {
            return IntStream.range(0, faker.number().numberBetween(min, max))
                    .mapToObj(i -> make(productModifiers))
                    .collect(Collectors.toList());
        }
    }
}
