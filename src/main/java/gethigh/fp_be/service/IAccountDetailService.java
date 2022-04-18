package gethigh.fp_be.service;

import gethigh.fp_be.model.AccountDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IAccountDetailService extends _IGeneralService<AccountDetail> {
    Optional<AccountDetail> findByAccount_Id(Long accountId);

    Page<AccountDetail> findAll(Pageable pageable);

    Page<AccountDetail> findAccountDetailByFullNameContaining(String fullName, Pageable pageable);
}
