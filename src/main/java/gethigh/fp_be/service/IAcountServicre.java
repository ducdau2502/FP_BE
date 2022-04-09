package gethigh.fp_be.service;

import gethigh.fp_be.model.Account;

import java.util.Optional;

public interface IAcountServicre {
    void findAll();
    void save(Account account);
    void delete(Long id);
    Optional<Account> findById(Long id);
}
