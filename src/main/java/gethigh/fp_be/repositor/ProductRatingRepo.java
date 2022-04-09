package gethigh.fp_be.repositor;

import gethigh.fp_be.model.ProductRating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRatingRepo extends JpaRepository<ProductRating,Long> {
}
