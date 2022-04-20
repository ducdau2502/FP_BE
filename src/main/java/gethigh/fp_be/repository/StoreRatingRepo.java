package gethigh.fp_be.repository;

import gethigh.fp_be.model.StoreRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreRatingRepo extends JpaRepository<StoreRating,Long> {

    Optional<StoreRating> findByAccount_IdAndStore_Id(Long account_id, Long store_id);

    Iterable<StoreRating> findAllByStore_Id(Long id);
}
