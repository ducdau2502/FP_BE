package gethigh.fp_be.service;

import gethigh.fp_be.model.ProductImage;

import java.util.Optional;

public interface IProductImageService extends _IGeneralService<ProductImage>{
//    Iterable<ProductImage> getProductImageByProductId(Long id);

    Iterable<ProductImage> findAllByProductId(Long id);

    Optional<ProductImage> findByProduct_Id(Long id);
}
