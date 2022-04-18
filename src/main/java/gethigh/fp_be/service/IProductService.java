package gethigh.fp_be.service;


import gethigh.fp_be.model.Product;

public interface IProductService extends _IGeneralService<Product> {
    Iterable<Product> findByName(String name);

    Iterable<Product> findAllByStore_Id(Long id);

    Iterable<Product> topProductSale();

    Iterable<Product> findAllByPriceIsGreaterThanEqualAndPriceIsLessThanEqual(Double lowerPrice, Double upperPrice);

    Iterable<Product> findAllByStore_IdAndNameContaining(Long id, String name);
}
