package gethigh.fp_be.repository;

import gethigh.fp_be.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreRepo extends JpaRepository<Store,Long> {
    Iterable<Store> findAllByNameContaining(String name);
}
