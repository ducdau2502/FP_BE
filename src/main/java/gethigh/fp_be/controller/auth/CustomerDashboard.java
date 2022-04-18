package gethigh.fp_be.controller.auth;

import gethigh.fp_be.model.*;
import gethigh.fp_be.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
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

    @Autowired
    private IStoreLikeService iStoreLikeService;

    @Autowired
    private IStoreService iStoreService;

    // test phân quyền
    @GetMapping("/show")
    private String showDashboard() {
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

    //thêm cửa hàng vào danh mục yêu thích
    @GetMapping("/like-store/{store_id}/{account_id}")
    public ResponseEntity<StoreLike> likeStore(@PathVariable("store_id") Long store_id,
                                               @PathVariable("account_id") Long account_id) {
        Optional<StoreLike> storeLikeOptional = iStoreLikeService.findByStore_IdAndAccountLike_Id(store_id, account_id);
        if (!storeLikeOptional.isPresent()) {
            Store store = iStoreService.findById(store_id).get();
            AccountDetail accountDetail = accountDetailService.findById(account_id).get();
            StoreLike storeLike = new StoreLike(accountDetail, store);
            iStoreLikeService.save(storeLike);
            return new ResponseEntity<>(storeLike, HttpStatus.OK);
        } else {
            iStoreLikeService.remove(storeLikeOptional.get().getId());
            return new ResponseEntity<>(storeLikeOptional.get(), HttpStatus.OK);
        }
    }

    //xem danh sách cửa hàng yêu thích
    @GetMapping("/list-store-like/{account_id}")
    public ResponseEntity<Iterable<Store>> findAllByAccountLike_Id(@PathVariable("account_id") Long account_id) {
        Iterable<StoreLike> storeLikes = iStoreLikeService.findAllByAccountLike_Id(account_id);
        List<Store> stores = new ArrayList<>();
        for (StoreLike store : storeLikes) {
            stores.add(store.getStore());
        }
        return new ResponseEntity<>(stores, HttpStatus.OK);
    }

    @GetMapping("/count-like/{store_id}")
    public ResponseEntity<Long> countLikeByStoreId(@PathVariable("store_id") Long store_id) {
        Long countLike = iStoreLikeService.countLikeStoreByStore_Id(store_id);
        return new ResponseEntity<>(countLike, HttpStatus.OK);
    }

    @GetMapping("/check/{store_id}/{account_id}")
    public ResponseEntity<Boolean> checkLikeStore(@PathVariable("store_id") Long store_id,
                                             @PathVariable("account_id") Long account_id) {
        boolean flag = false;
        Optional<StoreLike> storeLikeOptional = iStoreLikeService.findByStore_IdAndAccountLike_Id(store_id, account_id);
        if (storeLikeOptional.isPresent()) {
            flag = true;
        }
        return new ResponseEntity<>(flag, HttpStatus.OK);
    }
}
