package gethigh.fp_be.repository;

import gethigh.fp_be.model.AccountDetail;
import gethigh.fp_be.model.Cart;
import gethigh.fp_be.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
public interface CartRepo extends JpaRepository<Cart, Long> {
    Iterable<Cart> findAllByAccountDetail_Id(Long id);

    Cart findByAccountDetailAndProduct(AccountDetail accountDetail, Product product);

    @Transactional
    @Modifying
    void deleteByAccountDetail_IdAndProduct_Id(Long accountDetail_id, Long product_id);

    @Transactional
    @Modifying
    void deleteAllByAccountDetail_Id(Long id);
}
