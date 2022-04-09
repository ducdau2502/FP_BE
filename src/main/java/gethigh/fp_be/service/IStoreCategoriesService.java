package gethigh.fp_be.service;

import gethigh.fp_be.model.Product;
import gethigh.fp_be.model.StoreCategories;

import java.util.Optional;

public interface IStoreCategoriesService {
    Iterable<StoreCategories> findAll();
    void save(StoreCategories storeCategories);
    void delete(Long id);
    Optional<StoreCategories> findById(Long id);
}
