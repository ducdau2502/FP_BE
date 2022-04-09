package gethigh.fp_be.service.impl;

import gethigh.fp_be.model.Account;
import gethigh.fp_be.repositor.AccountRepo;
import gethigh.fp_be.service.IAcountServicre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService implements IAcountServicre {
    @Autowired
    AccountRepo accountRepo;

    @Override
    public Iterable<Account> findAll() {
       return accountRepo.findAll();
    }

    @Override
    public void save(Account account) {
        accountRepo.save(account);
    }

    @Override
    public void delete(Long id) {
        accountRepo.deleteById(id);
    }

    @Override
    public Optional<Account> findById(Long id) {
        return accountRepo.findById(id);
    }

}
