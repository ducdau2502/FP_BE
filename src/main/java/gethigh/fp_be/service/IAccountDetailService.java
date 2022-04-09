package gethigh.fp_be.service;

import gethigh.fp_be.model.Account;
import gethigh.fp_be.model.AccountDetail;

import java.util.Optional;

public interface IAccountDetailService {
    void findAll();
    void save(AccountDetail accountDetail);
    void delete(Long id);
    Optional<AccountDetail> findById(Long id);
}
