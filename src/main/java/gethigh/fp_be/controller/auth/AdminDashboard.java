package gethigh.fp_be.controller.auth;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth/admin/dashboard")
public class AdminDashboard {
//khu vực quản lý các tác vụ hệ thống - huydu
    @GetMapping("/show")
    private String showDashboard(){
        return " Admin Dashboard";
    }
}
