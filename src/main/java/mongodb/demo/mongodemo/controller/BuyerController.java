package mongodb.demo.mongodemo.controller;

import mongodb.demo.mongodemo.models.Buyer;
import mongodb.demo.mongodemo.services.BuyersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/buyers")
public class BuyerController {

    @Autowired
    private BuyersService buyersService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Buyer createBuyer(@RequestBody Buyer buyer) {
        return buyersService.createBuyer(buyer);
    }

    @GetMapping
    public List<Buyer> getAllBuyers() {
        return buyersService.getAllBuyers();
    }

    @GetMapping("/{id}")
    public Buyer getBuyer(@PathVariable String id) {
        return buyersService.getBuyer(id);
    }

    @GetMapping("/ageBetween")
    public List<Buyer> getBuyersWithAgeBetween(@RequestParam int minAge, @RequestParam int maxAge) {
        return buyersService.getBuyersWithAgeBetween(minAge, maxAge);
    }


    @PutMapping("/{id}")
    public Buyer updateBuyer(@PathVariable String id, @RequestBody Buyer updatedBuyer) {
        Buyer existingBuyer = buyersService.getBuyer(id);

        existingBuyer.setName(updatedBuyer.getName());
        existingBuyer.setAge(updatedBuyer.getAge());
        existingBuyer.setMale(updatedBuyer.getMale());
        existingBuyer.setEmail(updatedBuyer.getEmail());

        return buyersService.createBuyer(existingBuyer);
    }

    @GetMapping("/averageAge")
    public double getAverageAge() {
        List<Buyer> allBuyers = buyersService.getAllBuyers();

        if (allBuyers.isEmpty()) {
            return 0.0;
        }
        int totalAge = allBuyers.stream()
                .mapToInt(Buyer::getAge)
                .sum();
        double averageAge = (double) totalAge / allBuyers.size();
        return averageAge;
    }

    @DeleteMapping("/{id}")
    public void deleteBuyer(@PathVariable String id) {
        buyersService.deleteBuyer(id);
    }
    @DeleteMapping("/all")
    public void deleteAllBuyers() {
        buyersService.deleteAllBuyers();
    }
}