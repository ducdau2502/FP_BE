package gethigh.fp_be.repository;

import gethigh.fp_be.model.ProductFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductFeedbackRepo extends JpaRepository<ProductFeedback,Long> {
}
