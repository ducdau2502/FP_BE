package gethigh.fp_be.service.impl;

import gethigh.fp_be.model.AccountDetail;
import gethigh.fp_be.repositor.AccountDetailRepo;
import gethigh.fp_be.repositor.AccountRepo;
import gethigh.fp_be.service.IAccountDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountDetailService implements IAccountDetailService {

    @Autowired
    AccountDetailRepo accountDetailRepo;

    @Override
    public void findAll() {
        accountDetailRepo.findAll();
    }

    @Override
    public void save(AccountDetail accountDetail) {
        accountDetailRepo.save(accountDetail);
    }

    @Override
    public void delete(Long id) {
        accountDetailRepo.deleteById(id);
    }

    @Override
    public Optional<AccountDetail> findById(Long id) {
        return accountDetailRepo.findById(id);
    }
}
