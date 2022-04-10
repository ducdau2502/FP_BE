package gethigh.fp_be.repository;

import gethigh.fp_be.model.AccountRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRoleRepo extends JpaRepository<AccountRole,Long> {
}
