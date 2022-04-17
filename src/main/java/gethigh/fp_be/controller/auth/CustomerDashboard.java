package gethigh.fp_be.controller.auth;

import gethigh.fp_be.model.AccountDetail;
import gethigh.fp_be.model.Cart;
import gethigh.fp_be.model.Product;
import gethigh.fp_be.service.IAccountDetailService;
import gethigh.fp_be.service.ICartService;
import gethigh.fp_be.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("customer/dashboard")
public class CustomerDashboard {
//khu vực quản lý các tác vụ quản lý của khách hàng -huydu
    @Autowired
    private ICartService cartService;

    @Autowired
    private IAccountDetailService accountDetailService;

    @Autowired
    private IProductService productService;

    // test phân quyền
    @GetMapping("/show")
    private String showDashboard(){
        return " Customer Dashboard";
    }

    @PostMapping("/cart/{account_id}/{product_id}")
    public ResponseEntity<?> addCart(@PathVariable("account_id") Long account_id,
                                     @PathVariable("product_id") Long product_id) {
        AccountDetail accountDetail = accountDetailService.findById(account_id).get();
        cartService.addCart(product_id, accountDetail);
        return new ResponseEntity<>(accountDetail, HttpStatus.OK);
    }

    @GetMapping("/cart/{account_id}")
    public ResponseEntity<?> showCart(@PathVariable("account_id") Long id) {
        Iterable<Cart> carts = cartService.findAllByAccountDetail_Id(id);
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    @DeleteMapping("/cart/{account_id}/{product_id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("account_id") Long account_id,
                                           @PathVariable("product_id") Long product_id) {
        cartService.deleteByAccountDetail_IdAndProduct_Id(account_id, product_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/cart/{account_id}")
    public ResponseEntity<?> deleteCart(@PathVariable("account_id") Long account_id) {
        cartService.deleteAllByAccountDetail_Id(account_id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
