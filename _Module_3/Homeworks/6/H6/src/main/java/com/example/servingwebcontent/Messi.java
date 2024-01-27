package com.example.servingwebcontent;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Messi {

    @GetMapping("/mesi")
    public String mesi(){
        return "mesi";
    }

    @GetMapping("/mesi/goals")
    public String mesiGoals(
            @RequestParam(
                    name="name", required = false, defaultValue = "World"
            ) String name, Model model) {
        model.addAttribute("name", name);
        return "greeting_file";
    }

}
