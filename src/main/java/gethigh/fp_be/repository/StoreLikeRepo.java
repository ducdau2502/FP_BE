package gethigh.fp_be.repository;

import gethigh.fp_be.model.StoreLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreLikeRepo extends JpaRepository<StoreLike,Long> {
    Optional<StoreLike> findByStore_IdAndAccountLike_Id(Long store_id, Long account_id);

    Long countLikeStoreByStore_Id(Long store_id);

    Iterable<StoreLike> findAllByAccountLike_Id(Long id);
}
