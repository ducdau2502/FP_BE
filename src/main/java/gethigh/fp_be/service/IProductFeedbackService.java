package gethigh.fp_be.service;

import gethigh.fp_be.model.ProductFeedback;
import java.util.Optional;

public interface IProductFeedbackService {
    Iterable<ProductFeedback> findAll();
    void save(ProductFeedback productRating);
    void delete(Long id);
    Optional<ProductFeedback> findById(Long id);
}
