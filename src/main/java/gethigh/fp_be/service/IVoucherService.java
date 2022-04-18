package gethigh.fp_be.service;

import gethigh.fp_be.model.Voucher;

public interface IVoucherService extends _IGeneralService<Voucher> {
    Iterable<Voucher> findAllByStore_Id(Long id);

}
