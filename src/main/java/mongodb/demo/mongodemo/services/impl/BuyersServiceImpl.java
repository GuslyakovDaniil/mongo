package mongodb.demo.mongodemo.services.impl;

import mongodb.demo.mongodemo.models.Buyer;
import mongodb.demo.mongodemo.repos.BuyerRepository;
import mongodb.demo.mongodemo.services.BuyersService;
import mongodb.demo.mongodemo.utils.ClientErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuyersServiceImpl implements BuyersService {
    private final BuyerRepository buyersRepo;

    @Autowired
    public BuyersServiceImpl(BuyerRepository buyersRepo) {
        this.buyersRepo = buyersRepo;
    }
    @Override
    public Buyer createBuyer(Buyer buyer) {
        return buyersRepo.save(buyer);
    }

    @Override
    public Page<Buyer> getBuyers(Pageable pageable) {
        return buyersRepo.findAll(pageable);
    }

    @Override
    public List<Buyer> getAllBuyers() {
        return buyersRepo.findAll();
    }

    @Override
    public Buyer getBuyer(String id) {
        return buyersRepo.findById(id)
                .orElseThrow(() -> new ClientErrorException.NotFoundException("Покупатель с id=[%s] не найден", id));
    }
    @Override
    public List<Buyer> getBuyersWithNullNameAndAgeAbove(int age) {
        return buyersRepo.findByNameNullAndAgeAfter(age);
    }

    @Override
    public List<Buyer> getBuyersWithAgeBetween(int starting, int ending) {
        return buyersRepo.findByAgeBetween(starting, ending);
    }


    @Override
    public void deleteBuyer(String id) {
        buyersRepo.deleteById(id);
    }

    @Override
    public void deleteAllBuyers() {
        buyersRepo.deleteAll();
    }

    @Override
    public void saveAllBuyers(List<Buyer> buyers) {
        buyersRepo.saveAll(buyers);
    }

    @Override
    public Page<Buyer> findBuyersMatchingAll(Buyer probe, Pageable pageable) {
        ExampleMatcher matcher = ExampleMatcher.matchingAll();
        Example<Buyer> example = Example.of(probe, matcher);
        return buyersRepo.findAll(example, pageable);
    }

}
