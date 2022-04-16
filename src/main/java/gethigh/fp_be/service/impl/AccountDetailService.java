package gethigh.fp_be.service.impl;

import gethigh.fp_be.model.AccountDetail;
import gethigh.fp_be.repository.AccountDetailRepo;
import gethigh.fp_be.service.IAccountDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountDetailService implements IAccountDetailService {

    @Autowired
    AccountDetailRepo accountDetailRepo;


    @Override
    public Iterable<AccountDetail> findAll() {
        return accountDetailRepo.findAll();
    }

    @Override
    public Optional<AccountDetail> findById(Long id) {
        return accountDetailRepo.findById(id);
    }

    @Override
    public AccountDetail save(AccountDetail accountDetail) {
        return accountDetailRepo.save(accountDetail);
    }

    @Override
    public void remove(Long id) {
        accountDetailRepo.deleteById(id);
    }

    @Override
    public Page<AccountDetail> findAll(Pageable pageable) {
        return accountDetailRepo.findAll(pageable);
    }

    @Override
    public Page<AccountDetail> findAccountDetailByFullNameContaining(String fullName, Pageable pageable) {
        return accountDetailRepo.findAccountDetailByFullNameContaining(fullName, pageable);
    }

    @Override
    public Optional<AccountDetail> findByAccount_Id(Long accountId) {
        return accountDetailRepo.findByAccount_Id(accountId);
    }
}
