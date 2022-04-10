package gethigh.fp_be.service.impl;


import gethigh.fp_be.model.ProductImage;
import gethigh.fp_be.repository.ProductImageRepo;
import gethigh.fp_be.service.IProductImageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductImageService implements IProductImageService {
    @Autowired
    ProductImageRepo productImageRepo;


    @Override
    public Iterable<ProductImage> findAll() {
        return productImageRepo.findAll();
    }

    @Override
    public void save(ProductImage productImage) {
        productImageRepo.save(productImage);
    }

    @Override
    public void delete(Long id) {
        productImageRepo.deleteById(id);
    }

    @Override
    public Optional<ProductImage> findById(Long id) {
        return productImageRepo.findById(id);
    }
}
