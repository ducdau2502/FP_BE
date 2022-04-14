package gethigh.fp_be.service;

import gethigh.fp_be.model.AccountDetail;

import java.util.Optional;

public interface IAccountDetailService extends _IGeneralService<AccountDetail> {
    Optional<AccountDetail> findByAccount_Id(Long accountId);
}
