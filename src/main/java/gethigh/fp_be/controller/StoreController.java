package gethigh.fp_be.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth/store")
public class StoreController {

    // khu vực thực hiện các tác vụ với store - huydu
    @GetMapping("/show")
    private String showPage(){
        return "  store page";
    }
}
