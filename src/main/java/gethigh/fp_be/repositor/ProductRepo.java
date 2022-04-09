package gethigh.fp_be.repositor;

import gethigh.fp_be.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product,Long> {
}
