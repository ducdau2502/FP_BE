package gethigh.fp_be.service.impl;

import gethigh.fp_be.model.Voucher;
import gethigh.fp_be.repository.VoucherRepo;
import gethigh.fp_be.service.IVoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VoucherService implements IVoucherService {

    @Autowired
    private VoucherRepo voucherRepo;

    @Override
    public Iterable<Voucher> findAllByStore_Id(Long id) {
        return voucherRepo.findAllByStore_Id(id);
    }

    @Override
    public Iterable<Voucher> findAll() {
        return voucherRepo.findAll();
    }

    @Override
    public Optional<Voucher> findById(Long id) {
        return voucherRepo.findById(id);
    }

    @Override
    public Voucher save(Voucher voucher) {
        return voucherRepo.save(voucher);
    }

    @Override
    public void remove(Long id) {
        voucherRepo.deleteById(id);
    }
}
