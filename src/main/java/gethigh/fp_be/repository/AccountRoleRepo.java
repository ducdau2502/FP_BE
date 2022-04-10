package gethigh.fp_be.repository;

import gethigh.fp_be.model.AccountRole;
import gethigh.fp_be.model.num.EAccountRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRoleRepo extends JpaRepository<AccountRole,Long> {
    Optional<AccountRole> findByName(EAccountRole roleName);
}
