package mongodb.demo.mongodemo.repos;

import mongodb.demo.mongodemo.models.Buyer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BuyerRepository extends MongoRepository<Buyer, String> {
    List<Buyer> findByName(String name);
    List<Buyer> findByNameNot(String name);
    List<Buyer> findByNameContaining(String name);
    List<Buyer> findByNameNotContaining(String name);
    List<Buyer> findByNameMatches(String name);

    List<Buyer> findByAgeGreaterThanEqual(int age);

    @Query("{ 'age': { $gte: ?0, $lte: ?1 } }")
    List<Buyer> findByAgeBetween(int starting, int ending);
    List<Buyer> findByNameNullAndAgeAfter(int age);
    List<Buyer> findByNameStartingWith(String string);
}
