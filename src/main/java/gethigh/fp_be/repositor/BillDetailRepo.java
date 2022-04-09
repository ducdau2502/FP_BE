package gethigh.fp_be.repositor;

import gethigh.fp_be.model.BillDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillDetailRepo extends JpaRepository<BillDetail,Long> {
}
