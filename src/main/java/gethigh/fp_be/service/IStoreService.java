package gethigh.fp_be.service;


import gethigh.fp_be.model.Store;
import java.util.Optional;

public interface IStoreService {
    Iterable<Store> findAll();
    void save(Store store);
    void delete(Long id);
    Optional<Store> findById(Long id);
}
