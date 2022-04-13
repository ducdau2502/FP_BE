package gethigh.fp_be.controller;


import gethigh.fp_be.model.Account;
import gethigh.fp_be.model.Store;
import gethigh.fp_be.repository.AccountRepo;
import gethigh.fp_be.service.IAccountService;
import gethigh.fp_be.service.IStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {
    @Autowired
    IAccountService accountService;
    @Autowired
    IStoreService storeService;

    @GetMapping("/show-user")
    public ResponseEntity<List<Account>> showAllUser(){
        return  new ResponseEntity<>(accountService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/search-user/{name}")
    public ResponseEntity<List<Store>> showSearchStore(@PathVariable String name) {
        return new ResponseEntity<>(storeService.findStoreByName(name), HttpStatus.OK);
    }

    @GetMapping("/show-details-user/{id}")
    public ResponseEntity<Account> showDetailsUser(@PathVariable Long id) {
        return new ResponseEntity<>(accountService.findById1(id), HttpStatus.OK);
    }
}
