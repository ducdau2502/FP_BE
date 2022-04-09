package gethigh.fp_be.repositor;

import gethigh.fp_be.model.AccountDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDetailRepo extends JpaRepository<AccountDetail,Long> {
}
