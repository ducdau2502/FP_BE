package gethigh.fp_be.repository;

import gethigh.fp_be.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImageRepo extends JpaRepository<ProductImage,Long> {
    Iterable<ProductImage> getProductImagesByProductId(Long id);
}
