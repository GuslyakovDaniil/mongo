package mongodb.demo.mongodemo.services;

import mongodb.demo.mongodemo.models.Buyer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface BuyersService {
    Buyer createBuyer(Buyer buyer);
    Buyer getBuyer(String id);

    List<Buyer> getBuyersWithNullNameAndAgeAbove(int age);

    List<Buyer> getBuyersWithAgeBetween(int starting, int ending);

    void deleteBuyer(String id);
    void deleteAllBuyers();
    void saveAllBuyers(List<Buyer> buyers);
    Page<Buyer> getBuyers(Pageable pageable);
    List<Buyer> getAllBuyers();
    Page<Buyer> findBuyersMatchingAll(Buyer probe, Pageable pageable);
}
