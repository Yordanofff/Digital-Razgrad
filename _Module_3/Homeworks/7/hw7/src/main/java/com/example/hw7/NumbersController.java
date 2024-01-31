package com.example.hw7;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class NumbersController {
    private NumbersService numbersService;

    public NumbersController(NumbersService numbersService) {
        this.numbersService = numbersService;
    }

    @GetMapping("/numbers")
    public String numbers(@RequestParam int n, Model model) {
        List<Integer> all_numbers = numbersService.getAllNumbersFromOneThatDivideBy3(n);
        model.addAttribute("n", n);
        model.addAttribute("all_numbers", all_numbers);
        return "numbers";
    }
}
