package gethigh.fp_be.repository;

import gethigh.fp_be.model.StoreLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreLikeRepo extends JpaRepository<StoreLike,Long> {
}
