package gethigh.fp_be.repository;

import gethigh.fp_be.model.StoreRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRatingRepo extends JpaRepository<StoreRating,Long> {
}
