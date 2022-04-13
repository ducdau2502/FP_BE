package gethigh.fp_be.service;

import gethigh.fp_be.model.ProductImage;

public interface IProductImageService extends _IGeneralService<ProductImage>{
//    Iterable<ProductImage> getProductImageByProductId(Long id);

    Iterable<ProductImage> findAllByProductId(Long id);
}
