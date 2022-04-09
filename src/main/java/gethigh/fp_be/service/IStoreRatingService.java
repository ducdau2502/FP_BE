package gethigh.fp_be.service;

import gethigh.fp_be.model.Product;
import gethigh.fp_be.model.StoreRating;

import java.util.Optional;

public interface IStoreRatingService {
    Iterable<StoreRating> findAll();
    void save(StoreRating storeRating);
    void delete(Long id);
    Optional<StoreRating> findById(Long id);
}
