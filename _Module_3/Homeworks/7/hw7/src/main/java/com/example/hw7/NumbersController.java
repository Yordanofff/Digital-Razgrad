package com.example.hw7;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class NumbersController {
    private NumbersService numbersService;
    private SumNumbersService sumNumbersService;
    private WordService wordService;

    public NumbersController(NumbersService numbersService, SumNumbersService sumNumbersService, WordService wordService) {
        this.numbersService = numbersService;
        this.sumNumbersService = sumNumbersService;
        this.wordService = wordService;
    }

    @GetMapping("/numbers")
    public String numbers(@RequestParam int n, Model model) {
        List<Integer> all_numbers = numbersService.getAllNumbersFromOneThatDivideBy3(n);
        List<Integer> number_after_divide = numbersService.getDividersOfNumbersThatDivideBy3(all_numbers);
        model.addAttribute("n", n);
        model.addAttribute("all_numbers", all_numbers);
        model.addAttribute("numberService", numbersService);
        return "numbers";
    }

    @GetMapping("/sum-numbers")
    public String sumNumbers(@RequestParam int n, Model model) {
        model.addAttribute("n", n);
        model.addAttribute("sumNumbersService", sumNumbersService);
        return "sum_numbers";
    }

    @GetMapping("/words")
    public String printWordNTimes(@RequestParam String word, @RequestParam int n, Model model) {
        String word_n_times = wordService.printWordNTimes(word, n);
        model.addAttribute("word", word);
        model.addAttribute("n", n);
        model.addAttribute("word_n_times", word_n_times);
        return "words";
    }
}
