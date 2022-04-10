package gethigh.fp_be.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth/home")
public class HomeController {
// khu vực thực hiện các logic với trang chủ - huydu

    // test phân quyền
    @GetMapping("/show")
    private String showPage(){
        return " Home page";
    }
}
