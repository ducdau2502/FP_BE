package gethigh.fp_be.service.impl;

import gethigh.fp_be.model.Product;
import gethigh.fp_be.repository.ProductRepo;
import gethigh.fp_be.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    ProductRepo productRepo;

    @Override
    public Iterable<Product> findAll() {
        return productRepo.findAll();
    }

    @Override
    public Iterable<Product> findByName(String name) {
        return productRepo.findAllByNameContaining(name);
    }

    @Override
    public Iterable<Product> findAllByStore_Id(Long id) {
        return productRepo.findAllByStore_Id(id);
    }

    @Override
    public Iterable<Product> findAllByPriceIsGreaterThanEqualAndPriceIsLessThanEqual(Double lowerPrice, Double upperPrice) {
        return productRepo.findAllByPriceIsGreaterThanEqualAndPriceIsLessThanEqual(lowerPrice, upperPrice);
    }

    @Override
    public Iterable<Product> top3ProductSale() {
        return productRepo.top3ProductSale();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepo.findById(id);
    }

    @Override
    public Product save(Product product) {
        return productRepo.save(product);
    }

    @Override
    public void remove(Long id) {
        productRepo.deleteById(id);
    }
}
