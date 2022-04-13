package gethigh.fp_be.service;

import gethigh.fp_be.model.Account;

import java.util.List;
import java.util.Optional;

public interface IAccountService extends _IGeneralService<Account> {
   public List<Account> findAll();
     public Account findById1(Long id);
}
