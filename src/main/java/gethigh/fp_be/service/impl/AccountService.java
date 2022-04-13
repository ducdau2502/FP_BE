package gethigh.fp_be.service.impl;

import gethigh.fp_be.model.Account;
import gethigh.fp_be.repository.AccountRepo;
import gethigh.fp_be.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements UserDetailsService, IAccountService {

    @Autowired
    AccountRepo accountRepo;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Account not found with username " + username));
        return AccountDetailImpl.build(account) ;
    }


    @Override
    public List<Account> findAll() {
        return accountRepo.findAll();
    }

    @Override
    public Optional<Account> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Account findById1(Long id) {
        return accountRepo.findById(id).get();
    }


    @Override
    public Account save(Account account) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }
}
