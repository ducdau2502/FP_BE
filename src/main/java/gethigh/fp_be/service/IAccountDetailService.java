package gethigh.fp_be.service;

import gethigh.fp_be.model.AccountDetail;
import java.util.Optional;

public interface IAccountDetailService {
    Iterable<AccountDetail> findAll();
    void save(AccountDetail accountDetail);
    void delete(Long id);
    Optional<AccountDetail> findById(Long id);
}
