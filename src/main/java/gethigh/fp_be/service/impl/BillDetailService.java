package gethigh.fp_be.service.impl;

import gethigh.fp_be.model.BillDetail;
import gethigh.fp_be.repository.BillDetailRepo;
import gethigh.fp_be.service.IBillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BillDetailService implements IBillDetailService {
    @Autowired
    BillDetailRepo billDetailRepo;


    @Override
    public Iterable<BillDetail> findAll() {
        return billDetailRepo.findAll();
    }

    @Override
    public Optional<BillDetail> findById(Long id) {
        return billDetailRepo.findById(id);
    }

    @Override
    public BillDetail save(BillDetail billDetail) {
        return billDetailRepo.save(billDetail);
    }

    @Override
    public void remove(Long id) {
        billDetailRepo.deleteById(id);
    }
}
