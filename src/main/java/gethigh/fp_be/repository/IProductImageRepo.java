package gethigh.fp_be.repository;

import gethigh.fp_be.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductImageRepo extends JpaRepository<ProductImage,Long> {
}
