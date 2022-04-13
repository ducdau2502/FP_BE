package gethigh.fp_be.service.impl;


import gethigh.fp_be.model.ProductImage;
import gethigh.fp_be.repository.ProductImageRepo;
import gethigh.fp_be.service.IProductImageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class ProductImageService implements IProductImageService {
    @Autowired
    ProductImageRepo productImageRepo;

    @Override
    public Iterable<ProductImage> findAll() {
        return productImageRepo.findAll();
    }

    @Override
    public Optional<ProductImage> findById(Long id) {
        return productImageRepo.findById(id);
    }

    @Override
    public ProductImage save(ProductImage productImage) {
        return productImageRepo.save(productImage);
    }

    @Override
    public void remove(Long id) {
        productImageRepo.deleteById(id);
    }

    @Override
    public Iterable<ProductImage> getProductImageByProductId(Long id) {
        return productImageRepo.getProductImagesByProductId(id);
    }
}
