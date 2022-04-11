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
    public void save(Product product) {
        productRepo.save(product);
    }

    @Override
    public void delete(Long id) {
        productRepo.deleteById(id);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.empty();
    }
}
