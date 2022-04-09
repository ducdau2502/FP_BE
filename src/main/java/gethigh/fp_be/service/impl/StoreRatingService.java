package gethigh.fp_be.service.impl;

import gethigh.fp_be.model.StoreRating;
import gethigh.fp_be.repositor.StoreRatingRepo;
import gethigh.fp_be.service.IStoreRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StoreRatingService implements IStoreRatingService {
    @Autowired
    StoreRatingRepo storeRatingRepo;

    @Override
    public Iterable<StoreRating> findAll() {
        return storeRatingRepo.findAll();
    }

    @Override
    public void save(StoreRating storeRating) {
        storeRatingRepo.save(storeRating);
    }

    @Override
    public void delete(Long id) {
        storeRatingRepo.deleteById(id);
    }

    @Override
    public Optional<StoreRating> findById(Long id) {
        return storeRatingRepo.findById(id);
    }
}
