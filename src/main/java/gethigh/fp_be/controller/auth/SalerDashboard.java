package gethigh.fp_be.controller.auth;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth/saler/dashboard")
public class SalerDashboard {
//khu vực thực hiện các tác vụ quản lý với gian hàng - huydu

    // test phân quyền
    @GetMapping("/show")
    private String showDashboard(){
        return " Saler Dashboard";
    }
}
