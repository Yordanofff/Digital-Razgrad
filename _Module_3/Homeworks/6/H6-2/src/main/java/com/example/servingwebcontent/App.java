package com.example.servingwebcontent;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class App {
    @GetMapping("/print-numbers")
    public String app(@RequestParam(name = "n") int n, Model model) {
        String num1ToN = getNumbersFrom1ToN(n);
        model.addAttribute("n", n);
        model.addAttribute("numbers", num1ToN);
        return "numbersOneToN";
    }

    private String getNumbersFrom1ToN(int n) {
        StringBuilder num1ToN = new StringBuilder();
        for (int i = 1; i < n+1; i++) {
            num1ToN.append(i);
            if (i != n) {
                num1ToN.append(", ");
            }
        }
        return num1ToN.toString();
    }
}
