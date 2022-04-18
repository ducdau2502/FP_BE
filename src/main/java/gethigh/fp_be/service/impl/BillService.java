package gethigh.fp_be.service.impl;

import gethigh.fp_be.model.*;
import gethigh.fp_be.repository.*;
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

    @Autowired
    ProductRepo productRepo;

    @Autowired
    AccountDetailRepo accountDetailRepo;

    @Autowired
    VoucherRepo voucherRepo;

    @Autowired
    StoreRepo storeRepo;


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
    public boolean addBill(List<Cart> carts, Long account_id) {
        if (!carts.isEmpty()) {
            for (Cart cart : carts) {
                Bill bill = new Bill();
                Product product = productRepo.findById(cart.getProduct().getId()).get();
                Optional<Voucher> voucher = voucherRepo.findByStore_Id(cart.getProduct().getStore().getId());
                bill.setDateCreate(LocalDate.now());
                bill.setQuantity(cart.getQuantity());
                bill.setTotalPrice(cart.getTotalPrice());
                bill.setCustomer(cart.getAccountDetail());
                bill.setStore(cart.getProduct().getStore());
                bill.setProduct(product);
                voucher.ifPresent(bill::setVoucher);
                billRepo.save(bill);
                product.setInventoryQuantity((int) (product.getInventoryQuantity() - cart.getQuantity()));
                product.setSoldQuantity((int) (product.getSoldQuantity() + cart.getQuantity()));
                productRepo.save(product);
            }
            return true;
        }
        return false;
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
