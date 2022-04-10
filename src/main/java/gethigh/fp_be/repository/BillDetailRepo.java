package gethigh.fp_be.repository;

import gethigh.fp_be.model.BillDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillDetailRepo extends JpaRepository<BillDetail,Long> {
}
