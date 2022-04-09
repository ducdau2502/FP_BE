package gethigh.fp_be.service;

import gethigh.fp_be.model.Bill;
import java.util.Optional;

public interface IBillService {
    Iterable<Bill> findAll();
    void save(Bill bill);
    void delete(Long id);
    Optional<Bill> findById(Long id);
}
