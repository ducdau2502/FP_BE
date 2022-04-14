package gethigh.fp_be.repository;

import gethigh.fp_be.model.Product;
import gethigh.fp_be.model.ProductFeedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductFeedbackRepo extends JpaRepository<ProductFeedback,Long> {
    Iterable<ProductFeedback> findAllByProductFeedback_Id(long id);

    @Query(nativeQuery = true,value = "SELECT COUNT(product_feedback_id)" +
            "FROM product_feedback where product_feedback_id = :id")
    String countFeedbackByIdProduct(Long id);
}
