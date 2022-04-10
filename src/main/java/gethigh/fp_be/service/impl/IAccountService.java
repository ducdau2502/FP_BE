package gethigh.fp_be.service.impl;

import gethigh.fp_be.model.Account;

public interface IAccountService {
    void save(Account account);
    Account findById(Long id);
}
