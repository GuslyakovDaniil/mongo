package mongodb.demo.mongodemo.config;

import mongodb.demo.mongodemo.models.BuyerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BuyersConfiguration {
    @Bean
    public BuyerFactory buyersDtoFactory() {
        return new BuyerFactory();
    }
}
