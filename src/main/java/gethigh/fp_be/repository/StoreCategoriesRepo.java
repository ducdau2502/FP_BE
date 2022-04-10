package gethigh.fp_be.repository;

import gethigh.fp_be.model.StoreCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreCategoriesRepo extends JpaRepository<StoreCategories,Long> {
}
