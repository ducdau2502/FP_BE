package gethigh.fp_be.service;


import gethigh.fp_be.model.Product;

public interface IProductService extends _IGeneralService<Product> {
    Iterable<Product> findByName(String name);

    Iterable<Product> findAllByStore_Id(Long id);

    Iterable<Product> top3ProductSale();

    Iterable<Product> findAllByPriceIsGreaterThanEqualAndPriceIsLessThanEqual(Double lowerPrice, Double upperPrice);
}
