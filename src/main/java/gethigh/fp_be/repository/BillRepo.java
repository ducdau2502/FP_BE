package gethigh.fp_be.repository;

import gethigh.fp_be.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface BillRepo extends JpaRepository<Bill,Long> {

    Iterable<Bill> findAllByDateCreateBetweenAndStore_Id(LocalDate start, LocalDate end, Long id);

    Iterable<Bill> findAllByStore_Id(Long id);

    Iterable<Bill> findAllByCustomer_Id(Long id);
}
