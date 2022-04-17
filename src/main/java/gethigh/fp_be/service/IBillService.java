package gethigh.fp_be.service;

import gethigh.fp_be.model.Bill;

import java.time.LocalDate;

public interface IBillService extends _IGeneralService<Bill> {

    Iterable<Bill> findAllByDateCreateBetweenAndStore_Id(LocalDate start, LocalDate end, Long id);

    Iterable<Bill> findAllByStore_Id(Long id);
}
