package gethigh.fp_be.service.impl;

import gethigh.fp_be.model.StoreCategories;
import gethigh.fp_be.repository.StoreCategoriesRepo;
import gethigh.fp_be.service.IStoreCategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StoreCategoriesService implements IStoreCategoriesService {
    @Autowired
    StoreCategoriesRepo storeCategoriesRepo;


    @Override
    public Iterable<StoreCategories> findAll() {
        return storeCategoriesRepo.findAll();
    }

    @Override
    public Optional<StoreCategories> findById(Long id) {
        return storeCategoriesRepo.findById(id);
    }

    @Override
    public StoreCategories save(StoreCategories storeCategories) {
        return storeCategoriesRepo.save(storeCategories);
    }

    @Override
    public void remove(Long id) {
        storeCategoriesRepo.deleteById(id);
    }
}
