package gethigh.fp_be.controller.auth;

import gethigh.fp_be.model.*;
import gethigh.fp_be.service.*;
import gethigh.fp_be.model.AccountDetail;
import gethigh.fp_be.model.Bill;
import gethigh.fp_be.model.Cart;
import gethigh.fp_be.model.Product;
import gethigh.fp_be.service.IAccountDetailService;
import gethigh.fp_be.service.IBillService;
import gethigh.fp_be.service.ICartService;
import gethigh.fp_be.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
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
    private IStoreRatingService ratingService;

    @Autowired
    private IStoreLikeService iStoreLikeService;

    @Autowired
    private IStoreService iStoreService;

    @Autowired
    private IBillService billService;

    @Autowired
    private IVoucherService voucherService;

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

    @PostMapping("/plus/{account_id}/{product_id}")
    public ResponseEntity<?> plusQuantity(@PathVariable("account_id") Long account_id,
                                          @PathVariable("product_id") Long product_id) {
        AccountDetail accountDetail = accountDetailService.findById(account_id).get();
        cartService.plusQuantity(product_id, accountDetail);
        return new ResponseEntity<>(accountDetail, HttpStatus.OK);
    }

    @PostMapping("/minus/{account_id}/{product_id}")
    public ResponseEntity<?> minusQuantity(@PathVariable("account_id") Long account_id,
                                           @PathVariable("product_id") Long product_id) {
        AccountDetail accountDetail = accountDetailService.findById(account_id).get();
        cartService.minusQuantity(product_id, accountDetail);
        return new ResponseEntity<>(accountDetail, HttpStatus.OK);
    }

    @GetMapping("/cart/{account_id}")
    public ResponseEntity<?> showCart(@PathVariable("account_id") Long id) {
        Iterable<Cart> carts = cartService.findAllByAccountDetail_Id(id);
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    @GetMapping("/get-total/{account_id}")
    public ResponseEntity<?> getTotal(@PathVariable("account_id") Long id) {
        Double sum = cartService.getTotal(id);
        return new ResponseEntity<>(sum, HttpStatus.OK);
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

    @PostMapping("/pay/{account_id}")
    public ResponseEntity<?> pay(@PathVariable("account_id") Long account_id) {
        List<Cart> carts = (List<Cart>) cartService.findAllByAccountDetail_Id(account_id);
        boolean payCheck = billService.addBill(carts, account_id);
        if (payCheck) {
            cartService.deleteAllByAccountDetail_Id(account_id);
        }
        return new ResponseEntity<>(payCheck, HttpStatus.OK);
    }

    // create rating
    @PostMapping("/create-rating")
    public ResponseEntity<StoreRating> createRating(@RequestBody StoreRating storeRating) {
        Optional<StoreRating> storeRatingOptional = ratingService.findByAccount_IdAndStore_Id(storeRating.getAccount().getId(), storeRating.getStore().getId());
        if (storeRatingOptional.isPresent()) {
            ratingService.remove(storeRatingOptional.get().getId());
            ratingService.save(storeRating);
        }
        return new ResponseEntity<>(ratingService.save(storeRating), HttpStatus.OK);
    }

    @GetMapping("/get-discount/{store_id}")
    public ResponseEntity<?> getDiscount(@PathVariable("store_id") Long store_id) {
        Optional<Voucher> voucherOptional = voucherService.findById(store_id);
        if (!voucherOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(voucherOptional.get(), HttpStatus.OK);
    }

    @GetMapping("/get-bill/{customer_id}")
    public ResponseEntity<?> getAllBill(@PathVariable("customer_id") Long customer_id) {
        Iterable<Bill> bills = billService.findAllByCustomer_Id(customer_id);
        if (!bills.iterator().hasNext()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bills, HttpStatus.OK);
    }

    @GetMapping("/check-quantity/{customer_id}")
    public ResponseEntity<?> check(@PathVariable("customer_id") Long customer_id) {
        boolean check = cartService.checkQuantity(customer_id);
        return new ResponseEntity<>(check, HttpStatus.OK);
    }
}
