package gethigh.fp_be.controller.auth;

import gethigh.fp_be.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/saler/dashboard")
public class SalerDashboard {
//khu vực thực hiện các tác vụ quản lý với gian hàng - huydu

    // test phân quyền
    @GetMapping("/show")
    private String showDashboard(){
        return " Saler Dashboard";
    }

    @Autowired
    IProductService productService;


}
