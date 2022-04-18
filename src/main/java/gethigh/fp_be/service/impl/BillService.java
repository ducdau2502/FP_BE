package gethigh.fp_be.service.impl;

import gethigh.fp_be.model.Bill;
import gethigh.fp_be.model.Cart;
import gethigh.fp_be.model.Product;
import gethigh.fp_be.repository.BillRepo;
import gethigh.fp_be.repository.ProductRepo;
import gethigh.fp_be.service.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
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

    @Override
    public Iterable<Bill> findAllByDateCreateBetweenAndStore_Id(LocalDate start, LocalDate end, Long id) {
        return billRepo.findAllByDateCreateBetweenAndStore_Id(start, end, id);
    }

    @Override
    public Iterable<Bill> findAllByStore_Id(Long id) {
        return billRepo.findAllByStore_Id(id);
    }
}
