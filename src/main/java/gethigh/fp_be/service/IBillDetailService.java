package gethigh.fp_be.service;


import gethigh.fp_be.model.BillDetail;
import java.util.Optional;

public interface IBillDetailService {
    void findAll();
    void save(BillDetail billDetail);
    void delete(Long id);
    Optional<BillDetail> findById(Long id);
}
