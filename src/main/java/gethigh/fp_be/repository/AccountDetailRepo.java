package gethigh.fp_be.repository;

import gethigh.fp_be.model.AccountDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountDetailRepo extends JpaRepository<AccountDetail,Long> {
    Optional<AccountDetail> findByAccount_Id(Long accountId);

    Page<AccountDetail> findAll(Pageable pageable);
    Page<AccountDetail> findAccountDetailByFullNameContaining(String fullName, Pageable pageable);
}