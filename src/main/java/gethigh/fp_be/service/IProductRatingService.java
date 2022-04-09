package gethigh.fp_be.service;

import gethigh.fp_be.model.ProductRating;
import java.util.Optional;

public interface IProductRatingService {
    Iterable<ProductRating> findAll();
    void save(ProductRating productRating);
    void delete(Long id);
    Optional<ProductRating> findById(Long id);
}
