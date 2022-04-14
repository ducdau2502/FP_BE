package gethigh.fp_be.service.impl;

import gethigh.fp_be.model.ProductFeedback;
import gethigh.fp_be.repository.ProductFeedbackRepo;
import gethigh.fp_be.service.IProductFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductFeedbackService implements IProductFeedbackService {

    @Autowired
    ProductFeedbackRepo productFeedbackRepo;


    @Override
    public Iterable<ProductFeedback> findAll() {
        return productFeedbackRepo.findAll();
    }

    @Override
    public Optional<ProductFeedback> findById(Long id) {
        return productFeedbackRepo.findById(id);
    }

    @Override
    public ProductFeedback save(ProductFeedback productFeedback) {
        return productFeedbackRepo.save(productFeedback);
    }

    @Override
    public void remove(Long id) {
        productFeedbackRepo.deleteById(id);
    }

    @Override
    public Iterable<ProductFeedback> findAllByProductFeedback_Id(long id) {
        return productFeedbackRepo.findAllByProductFeedback_Id(id);
    }

    @Override
    public String countFeedbackByIdProduct(Long id) {
        return productFeedbackRepo.countFeedbackByIdProduct(id);
    }
}
