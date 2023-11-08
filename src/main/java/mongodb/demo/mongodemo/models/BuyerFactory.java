package mongodb.demo.mongodemo.models;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Component
public class BuyerFactory {
    private static AtomicInteger nextId = new AtomicInteger(1);
    private static AtomicInteger nextAge = new AtomicInteger(1);
    private final Faker faker = new Faker();

    public String name() {
        return faker.name().fullName();
    }

    public String male() {
        return faker.demographic().sex();
    }

    public String email() {
        return faker.internet().emailAddress();
    }

    public Buyer make(UnaryOperator<Buyer>... buyers) {
        final Buyer result = new Buyer(name(), nextAge.getAndAdd(1), male(), email());
        Stream.of(buyers).forEach(v -> v.apply(result));
        return result;
    }

    public static UnaryOperator<Buyer> oneUpId = s -> {
        s.setId(Integer.valueOf(nextId.getAndAdd(1)).toString());
        return s;
    };

    public BuyerListDTOFactory listBuilder() {
        return new BuyerListDTOFactory();
    }

    public class BuyerListDTOFactory {
        public List<Buyer> buyers(int min, int max, UnaryOperator<Buyer>... buyers) {
            return IntStream.range(0, faker.number().numberBetween(min, max))
                    .mapToObj(i -> make(buyers))
                    .collect(Collectors.toList());
        }
    }
}
