package gethigh.fp_be.repositor;

import gethigh.fp_be.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepo extends JpaRepository<Bill,Long> {
}
