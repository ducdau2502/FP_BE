package gethigh.fp_be.service.impl;

import gethigh.fp_be.model.ProductRating;
import gethigh.fp_be.repositor.ProductRatingRepo;
import gethigh.fp_be.service.IProductRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductRatingService implements IProductRatingService {

    @Autowired
    ProductRatingRepo productRatingRepo;


    @Override
    public Iterable<ProductRating> findAll() {
        return productRatingRepo.findAll();
    }

    @Override
    public void save(ProductRating productRating) {
        productRatingRepo.save(productRating);
    }

    @Override
    public void delete(Long id) {
        productRatingRepo.deleteById(id);
    }

    @Override
    public Optional<ProductRating> findById(Long id) {
        return productRatingRepo.findById(id);
    }
}
