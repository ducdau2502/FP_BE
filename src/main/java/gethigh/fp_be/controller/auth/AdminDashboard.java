package gethigh.fp_be.controller.auth;

import gethigh.fp_be.dto.request.AcceptRequest;
import gethigh.fp_be.model.Account;
import gethigh.fp_be.model.AccountDetail;
import gethigh.fp_be.model.AccountRole;
import gethigh.fp_be.model.Store;
import gethigh.fp_be.model.num.EAccountRole;
import gethigh.fp_be.repository.AccountRepo;
import gethigh.fp_be.repository.AccountRoleRepo;
import gethigh.fp_be.service.IStoreService;
import gethigh.fp_be.service.IAccountDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin/dashboard")
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

    // test phân quyền
    @GetMapping("/show")
    private String showDashboard() {
        return " Admin Dashboard";
    }

    // xác nhận đăng kí với tài khoản gian hàng
    @PostMapping("/accept/account/{id}")
    private ResponseEntity<?> acceptSaler(@PathVariable("id") Long id, @RequestBody AcceptRequest acceptRequest) {
        Optional<Account> account = accountRepo.findById(id);
        Set<String> strRoles = acceptRequest.getRole();
        Set<AccountRole> roles = new HashSet<>();
        Store store = new Store();

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
                case "saler":
                    AccountRole salerRole = roleRepo.findByName(EAccountRole.ROLE_SALER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(salerRole);
                    break;
            }
        });
        Set<AccountRole> accountRoles = account.get().getRoles();
        roles.addAll(accountRoles);

        account.get().setRoles(roles);
        Account accountNew = new Account(account.get().getId(),
                account.get().getUsername(),
                account.get().getPassword(),
                account.get().getEmail(),
                account.get().getRoles());
        store.setName(acceptRequest.getNameStore());
        store.setDescription(acceptRequest.getDescription());

        iStoreService.save(store);
        accountRepo.save(accountNew);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    //Block gian hàng
    @PutMapping("/block-store/{store_id}")
    public ResponseEntity<?> blockStore(@PathVariable("store_id") Long store_id) {
        Optional<AccountDetail> accountDetail = accountDetailService.findById(store_id);
        if (!accountDetail.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        accountDetail.get().setStatus("blocked");
        accountDetailService.save(accountDetail.get());
        return new ResponseEntity<>(accountDetail, HttpStatus.OK);
    }
}
