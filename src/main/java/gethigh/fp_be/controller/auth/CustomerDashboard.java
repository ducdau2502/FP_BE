package gethigh.fp_be.controller.auth;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth/customer/dashboard")
public class CustomerDashboard {
//khu vực quản lý các tác vụ quản lý của khách hàng -huydu

    // test phân quyền
    @GetMapping("/show")
    private String showDashboard(){
        return " Customer Dashboard";
    }
}
