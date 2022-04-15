package gethigh.fp_be.service.impl;

import gethigh.fp_be.dto.response.TopStoreSale;
import gethigh.fp_be.model.Store;
import gethigh.fp_be.repository.StoreRepo;
import gethigh.fp_be.service.IStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StoreService implements IStoreService {
    @Autowired
    StoreRepo storeRepo;


    @Override
    public Iterable<Store> findAll() {
        return storeRepo.findAll();
    }

    @Override
    public Optional<Store> findById(Long id) {
        return storeRepo.findById(id);
    }

    @Override
    public Store save(Store store) {
        return storeRepo.save(store);
    }

    @Override
    public void remove(Long id) {
        storeRepo.deleteById(id);
    }

    @Override
    public Optional<Store> findStoreByStoreOwner_Id(Long id) {
        return storeRepo.findStoreByStoreOwner_Id(id);
    }

    @Override
    public Iterable<Store> findAllByCategoriesList_Id(Long id) {
        return storeRepo.findAllByCategoriesList_Id(id);
    }

    @Override
    public Iterable<Store> topStoreSale() {
        Iterable<Long> storeId = storeRepo.topStoreSale();
        List<Store> topStoreSale = new ArrayList<>();
        for(Long id : storeId) {
            topStoreSale.add(findById(id).get());
        }
        return topStoreSale;
    }

    @Override
    public Iterable<Store> findAllByNameContaining(String name) {
        return storeRepo.findAllByNameContaining(name);
    }
}
