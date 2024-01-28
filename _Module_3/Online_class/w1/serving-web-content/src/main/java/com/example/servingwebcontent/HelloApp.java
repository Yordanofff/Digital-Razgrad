package com.example.servingwebcontent;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloApp {
    @GetMapping("/")
        public String yo(
                @RequestParam(
                        name="name", required = false, defaultValue = "World"
                ) String name, Model model) {
        model.addAttribute("name", name);
        return "greeting_file";
    }

    @GetMapping("/user")
    public String user(@RequestParam(name="name") String name, @RequestParam(name="age") int age, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        return "user";
    }

    @GetMapping("/addnums")
    public String user(@RequestParam(name="n") int n, @RequestParam(name="m") int m, Model model) {
        int result = n + m;
        model.addAttribute("result", result);
        return "add";
    }

}
