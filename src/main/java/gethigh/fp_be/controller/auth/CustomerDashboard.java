package gethigh.fp_be.controller.auth;

import gethigh.fp_be.model.AccountDetail;
import gethigh.fp_be.model.Store;
import gethigh.fp_be.model.StoreLike;
import gethigh.fp_be.service.IAccountDetailService;
import gethigh.fp_be.service.IStoreLikeService;
import gethigh.fp_be.service.IStoreService;
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
    private IStoreLikeService iStoreLikeService;

    @Autowired
    private IStoreService iStoreService;

    @Autowired
    private IAccountDetailService iAccountDetailService;

    // test phân quyền
    @GetMapping("/show")
    private String showDashboard(){
        return " Customer Dashboard";
    }

    //tổng like của 1 store
    @GetMapping("/count-like-store/{id_store}")
    public ResponseEntity<Long> countLikeByStoreId(@PathVariable("id_store") Long id_store) {
        Long countLikeByStoreId = iStoreLikeService.countLikeStoreByStore_Id(id_store);
        return new ResponseEntity<>(countLikeByStoreId, HttpStatus.OK);
    }

    //list store like
    @GetMapping("/list-store-like/{id_account}")
    public ResponseEntity<Iterable<StoreLike>> findAllByAccount_Id(@PathVariable("id_account") Long id_account) {
        Iterable<StoreLike> storeLike = iStoreLikeService.findAllByAccountLike_Id(id_account);
        if (storeLike.iterator().hasNext()) {
            return new ResponseEntity<>(storeLike, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //like store
    @PostMapping("/{store_id}/{account_id}")
    public ResponseEntity<StoreLike> checkLikeStore(@PathVariable("store_id") Long store_id,
                                                      @PathVariable("account_id") Long account_id) {
        Optional<StoreLike> storeLikeOptional = iStoreLikeService.findByStore_IdAndAccountLike_Id(store_id, account_id);
        if (!storeLikeOptional.isPresent()) {
            Store store = iStoreService.findById(store_id).get();
            AccountDetail accountDetail = iAccountDetailService.findById(account_id).get();
            StoreLike storeLike = new StoreLike(accountDetail, store);
            iStoreLikeService.save(storeLike);
            return new ResponseEntity<>(storeLike, HttpStatus.OK);
        } else {
            iStoreLikeService.remove(storeLikeOptional.get().getId());
            return new ResponseEntity<>(storeLikeOptional.get(), HttpStatus.OK);
        }
    }

}
