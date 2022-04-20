package gethigh.fp_be.service.impl;

import gethigh.fp_be.model.AccountDetail;
import gethigh.fp_be.model.Cart;
import gethigh.fp_be.model.Product;
import gethigh.fp_be.repository.CartRepo;
import gethigh.fp_be.repository.ProductRepo;
import gethigh.fp_be.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements ICartService {
    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private ProductRepo productRepo;

    @Override
    public Iterable<Cart> findAll() {
        return cartRepo.findAll();
    }

    @Override
    public Optional<Cart> findById(Long id) {
        return cartRepo.findById(id);
    }

    @Override
    public Cart save(Cart cart) {
        return cartRepo.save(cart);
    }

    @Override
    public void remove(Long id) {
        cartRepo.deleteById(id);
    }

    @Override
    public Iterable<Cart> findAllByAccountDetail_Id(Long id) {
        return cartRepo.findAllByAccountDetail_Id(id);
    }

    @Override
    public void deleteByAccountDetail_IdAndProduct_Id(Long accountDetail_id, Long product_id) {
        cartRepo.deleteByAccountDetail_IdAndProduct_Id(accountDetail_id, product_id);
    }

    @Override
    public void deleteAllByAccountDetail_Id(Long id) {
        cartRepo.deleteAllByAccountDetail_Id(id);
    }

    @Override
    public void addCart(Long product_id, AccountDetail accountDetail) {
        Product product = productRepo.findById(product_id).get();
        Cart cart = cartRepo.findByAccountDetailAndProduct(accountDetail, product);
        if (cart != null) {
            cart.setQuantity(cart.getQuantity() + 1);
        } else {
            cart = new Cart();
            cart.setProduct(product);
            cart.setAccountDetail(accountDetail);
            cart.setQuantity(1L);
        }
        cartRepo.save(cart);
    }

    @Override
    public void minusQuantity(Long product_id, AccountDetail accountDetail) {
        Product product = productRepo.findById(product_id).get();
        Cart cart = cartRepo.findByAccountDetailAndProduct(accountDetail, product);
        cart.setQuantity(cart.getQuantity() - 1);
        cartRepo.save(cart);
    }

    @Override
    public void plusQuantity(Long product_id, AccountDetail accountDetail) {
        Product product = productRepo.findById(product_id).get();
        Cart cart = cartRepo.findByAccountDetailAndProduct(accountDetail, product);
        cart.setQuantity(cart.getQuantity() + 1);
        cartRepo.save(cart);
    }

    @Override
    public Double getTotal(Long account_id) {
        Double sum = Double.valueOf(0);
        List<Cart> cartList = (List<Cart>) cartRepo.findAllByAccountDetail_Id(account_id);
        for (Cart cart : cartList) {
            sum += cart.getTotalPrice();
        }
        return sum;
    }

    @Override
    public boolean checkQuantity(Long account_id) {
        boolean check = true;
        List<Cart> cartList = (List<Cart>) cartRepo.findAllByAccountDetail_Id(account_id);
        for (Cart cart : cartList) {
            if (cart.getQuantity() > cart.getProduct().getInventoryQuantity()) {
                check = false;
                break;
            }
        }
        return check;
    }
}
