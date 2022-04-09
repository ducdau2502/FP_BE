package gethigh.fp_be.repositor;

import gethigh.fp_be.model.StoreCategories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreCategoriesRepo extends JpaRepository<StoreCategories,Long> {
}
