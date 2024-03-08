package com.h12.h12.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    @GetMapping({"/about", "/"})
    String index() {
        return "index";
    }

    @GetMapping("/login")
    String login() {
        return "login";
    }

    @GetMapping("/user_info")
    String getUserInfo(){
        return "user_info";
    }

//    @PostMapping("/login")
//    String loginPost(@RequestParam("username") String username, @RequestParam("password") String password) {
//        System.out.println(username);
//        System.out.println(password);
//        return "redirect:/";
//    }
}
