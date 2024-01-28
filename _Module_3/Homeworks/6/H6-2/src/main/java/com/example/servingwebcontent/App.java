package com.example.servingwebcontent;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class App {
    @GetMapping("/print-numbers")
    public String app(
            @RequestParam(name = "n") int n,
            @RequestParam(name = "m", required = false) Integer m,
            Model model) {
        String num1ToN = getNumbersFrom1ToN(n);
        model.addAttribute("n", n);
        model.addAttribute("numbers", num1ToN);
        if (m == null) {
            return "numbersOneToN";
        }
        String numbersNtoM = getEvenNumbersFromNtoM(n, m);
        model.addAttribute("m", m);
        model.addAttribute("numbersNtoM", numbersNtoM);
        return "evenNumbersNToM";
    }

    @GetMapping("/")
    private String home(){
        return "home";
    }

    private String getNumbersFrom1ToN(int n) {
        if (n < 1) {
            return "N: [" + n + "] needs to be bigger than 1.";
        }
        StringBuilder num1ToN = new StringBuilder();
        for (int i = 1; i < n + 1; i++) {
            num1ToN.append(i);
            if (i != n) {
                num1ToN.append(", ");
            }
        }
        return num1ToN.toString();
    }

    private String getEvenNumbersFromNtoM(int n, int m) {
        if (m < n) {
            return "M: [" + m + "] needs to be bigger than N: [" + n + "]";
        }
        StringBuilder evenNumbers = new StringBuilder();
        for (int i = n; i <= m; i++) {
            if (i % 2 == 0) {
                evenNumbers.append(i);
                evenNumbers.append(", ");
            }
        }
        evenNumbers.deleteCharAt(evenNumbers.length() - 1);
        evenNumbers.deleteCharAt(evenNumbers.length() - 1);
        return evenNumbers.toString();
    }
}
