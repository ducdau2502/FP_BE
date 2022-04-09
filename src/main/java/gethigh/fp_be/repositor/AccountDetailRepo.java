package gethigh.fp_be.repositor;

import gethigh.fp_be.model.AccountDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDetailRepo extends JpaRepository<AccountDetail,Long> {
}