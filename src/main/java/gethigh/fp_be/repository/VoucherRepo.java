package gethigh.fp_be.repository;

import gethigh.fp_be.model.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoucherRepo extends JpaRepository<Voucher, Long> {

    Iterable<Voucher> findAllByStore_Id(Long id);

    Optional<Voucher> findByStore_Id(Long id);
}
