package gethigh.fp_be.controller.auth;

import gethigh.fp_be.dto.request.AcceptRequest;
import gethigh.fp_be.model.*;
import gethigh.fp_be.model.num.EAccountRole;
import gethigh.fp_be.repository.AccountRepo;
import gethigh.fp_be.repository.AccountRoleRepo;
import gethigh.fp_be.service.IStoreService;
import gethigh.fp_be.service.IAccountDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping("admin/dashboard")
public class AdminDashboard {
//khu vực quản lý các tác vụ hệ thống - huydu

    @Autowired
    AccountRepo accountRepo;

    @Autowired
    AccountRoleRepo roleRepo;

    @Autowired
    IAccountDetailService accountDetailService;

    @Autowired
    IStoreService iStoreService;

    @Autowired
    IStoreService service;

    private Sort.Direction getSortDirection(String direction) {
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }

        return Sort.Direction.ASC;
    }

    // test phân quyền
    @GetMapping("/show")
    private String showDashboard() {
        return " Admin Dashboard";
    }

    // xác nhận đăng kí với tài khoản gian hàng
    @PostMapping("/accept/account")
    private ResponseEntity<?> acceptSaler (@RequestBody AcceptRequest acceptRequest) {
        Optional<Account> account = accountRepo.findById(acceptRequest.getIdAcc());
        Set<String> strRoles = acceptRequest.getRole();
        Set<AccountRole> roles = new HashSet<>();

        if (!account.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        strRoles.forEach(role -> {
            switch (role) {
                case "admin":
                    AccountRole adminRole = roleRepo.findByName(EAccountRole.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(adminRole);
                    break;
                case "seller":
                    AccountRole sellerRole = roleRepo.findByName(EAccountRole.ROLE_SELLER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(sellerRole);
                    break;
            }
        });

        account.get().setRoles(roles);
        Account accountNew = new Account(account.get().getId(),
                account.get().getUsername(),
                account.get().getPassword(),
                account.get().getEmail(),
                account.get().getRoles());
        accountRepo.save(accountNew);
        Optional<AccountDetail> accountDetail = accountDetailService.findByAccount_Id(account.get().getId());
//        AccountDetail accountDetailnew = new AccountDetail(account.get().getId(),"Activated");
        accountDetail.get().setStatus("Activated");
        accountDetailService.save(accountDetail.get());
        Store store = new Store();
        store.setStoreOwner(accountDetail.get());
        iStoreService.save(store);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    //Block gian hàng
    @GetMapping("/block-store/{store_id}")
    public ResponseEntity<?> blockStore(@PathVariable("store_id") Long store_id) {
        Optional<AccountDetail> accountDetail = accountDetailService.findById(store_id);
        if (!accountDetail.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (accountDetail.get().getStatus().equals("Blocked")) {
            accountDetail.get().setStatus("Activated");
        } else if (accountDetail.get().getStatus().equals("Activated")) {
            accountDetail.get().setStatus("Blocked");
        }
        accountDetailService.save(accountDetail.get());
        return new ResponseEntity<>(accountDetail, HttpStatus.OK);
    }

    //xem danh sách tài khoản trong hệ thống
//    @GetMapping("/list-account")
//    public ResponseEntity<Iterable<AccountDetail>> findAllAccount() {
//        Iterable<AccountDetail> accountDetails = accountDetailService.findAll();
//        if (accountDetails.iterator().hasNext()) {
//            return new ResponseEntity<>(accountDetails, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }

    @GetMapping("/list-account")
    public ResponseEntity<Map<String, Object>> getListAccount(
        @RequestParam(required = false) String fullName,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "3") int size,
        @RequestParam(defaultValue = "id,desc") String[] sort) {

            try {
                List<Order> orders = new ArrayList<>();

                if (sort[0].contains(",")) {
                    for (String sortOrder : sort) {
                        String[] _sort = sortOrder.split(",");
                        orders.add(new Order(getSortDirection(_sort[1]), _sort[0]));
                    }
                } else {
                    // sort=[field, direction]
                    orders.add(new Order(getSortDirection(sort[1]), sort[0]));
                }

                List<AccountDetail> accountDetails = new ArrayList<>();
                Pageable pagingSort = PageRequest.of(page, size, Sort.by(orders));

                Page<AccountDetail> pageAccountDetails;
                if (fullName == null) {
                    pageAccountDetails = accountDetailService.findAll(pagingSort);
                } else {
                    pageAccountDetails = accountDetailService.findAccountDetailByFullNameContaining(fullName, pagingSort);
                }
                accountDetails = pageAccountDetails.getContent();

                Map<String, Object> response = new HashMap<>();
                response.put("accounts", accountDetails);
                response.put("currentPage", pageAccountDetails.getNumber());
                response.put("totalItems", pageAccountDetails.getTotalElements());
                response.put("totalPages", pageAccountDetails.getTotalPages());

                return new ResponseEntity<>(response, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

    //xem danh sách cửa hành
    @GetMapping("/list-store")
    public ResponseEntity<Iterable<Store>> findAllStore() {
        Iterable<Store> stores = iStoreService.findAll();
        if (stores.iterator().hasNext()) {
            return new ResponseEntity<>(stores, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //tìm kiếm theo tên cửa hàng
    @GetMapping("/search-store")
    public ResponseEntity<Iterable<Store>> findStoreByName(@RequestParam("name") String name) {
        Iterable<Store> stores = iStoreService.findAllByNameContaining(name);
        if (!stores.iterator().hasNext()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(stores, HttpStatus.OK);
        }
    }

    //Xem chi tiết thông tin tài khoản
    @GetMapping("/detail-account/{id}")
    public ResponseEntity<AccountDetail> findAccountById(@PathVariable("id") Long id) {
        Optional<AccountDetail> accountDetail = accountDetailService.findById(id);
        if (accountDetail.isPresent()) {
            return new ResponseEntity<>(accountDetail.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
