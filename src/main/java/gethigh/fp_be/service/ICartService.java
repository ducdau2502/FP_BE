package gethigh.fp_be.service;

import gethigh.fp_be.model.AccountDetail;
import gethigh.fp_be.model.Cart;
import gethigh.fp_be.model.Product;

import java.util.Optional;

public interface ICartService extends _IGeneralService<Cart> {
    Iterable<Cart> findAllByAccountDetail_Id(Long id);

    void deleteByAccountDetail_IdAndProduct_Id(Long accountDetail_id, Long product_id);

    void deleteAllByAccountDetail_Id(Long id);

    void addCart(Long product_id, AccountDetail accountDetail);

    void minusQuantity(Long product_id, AccountDetail accountDetail);

    void plusQuantity(Long product_id, AccountDetail accountDetail);

    Double getTotal(Long account_id);

    boolean checkQuantity(Long account_id);
}
