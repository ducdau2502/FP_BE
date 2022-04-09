package gethigh.fp_be.repositor;

import gethigh.fp_be.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepo extends JpaRepository<Store,Long> {
}
