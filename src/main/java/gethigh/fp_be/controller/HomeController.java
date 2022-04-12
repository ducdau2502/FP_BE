package gethigh.fp_be.controller;

import gethigh.fp_be.dto.response.MessageResponse;
import gethigh.fp_be.model.Store;
import gethigh.fp_be.service.IStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("api/auth/home")
public class HomeController {

    @Autowired
    private IStoreService storeService;
// khu vực thực hiện các logic với trang chủ - huydu

    // test phân quyền
    @GetMapping("/show")
    private String showPage(){
        return " Home page";
    }

    @GetMapping("/find_store/{id}")
    public ResponseEntity<?> findStoreById(@PathVariable("id") long id) {
        Optional<Store> storeOptional = storeService.findById(id);
        if (storeOptional.isPresent()) {
            return new ResponseEntity<>(storeOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new MessageResponse("store not found"), HttpStatus.NOT_FOUND);
        }
    }


}
