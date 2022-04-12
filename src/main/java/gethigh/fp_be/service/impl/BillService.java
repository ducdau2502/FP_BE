package gethigh.fp_be.service.impl;

import gethigh.fp_be.model.Bill;
import gethigh.fp_be.repository.BillRepo;
import gethigh.fp_be.service.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BillService implements IBillService {
    @Autowired
    BillRepo billRepo;


    @Override
    public Iterable<Bill> findAll() {
        return billRepo.findAll();
    }

    @Override
    public Optional<Bill> findById(Long id) {
        return billRepo.findById(id);
    }

    @Override
    public Bill save(Bill bill) {
        return billRepo.save(bill);
    }

    @Override
    public void remove(Long id) {
        billRepo.deleteById(id);
    }
}
