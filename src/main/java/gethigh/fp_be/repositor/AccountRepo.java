package gethigh.fp_be.repositor;

import gethigh.fp_be.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepo extends JpaRepository<Account,Long> {
}
