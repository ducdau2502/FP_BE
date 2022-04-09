package gethigh.fp_be.service;

import gethigh.fp_be.model.ProductImage;
import java.util.Optional;

public interface IProductImageService {

    Iterable<ProductImage> findAll();
    void save(ProductImage productImage);
    void delete(Long id);
    Optional<ProductImage> findById(Long id);
}
