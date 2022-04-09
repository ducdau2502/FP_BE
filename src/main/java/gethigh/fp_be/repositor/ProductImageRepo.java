package gethigh.fp_be.repositor;

import gethigh.fp_be.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepo extends JpaRepository<ProductImage,Long> {
}
