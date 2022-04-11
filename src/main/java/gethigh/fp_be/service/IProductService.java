package gethigh.fp_be.service;


import gethigh.fp_be.model.Product;
import java.util.Optional;

public interface IProductService {
    Iterable<Product> findAll();
    Iterable<Product> findByName(String name);
    void save(Product product);
    void delete(Long id);
    Optional<Product> findById(Long id);
}
