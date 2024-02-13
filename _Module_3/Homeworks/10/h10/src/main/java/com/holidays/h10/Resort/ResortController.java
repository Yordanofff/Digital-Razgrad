package com.holidays.h10.Resort;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ResortController {
    @Autowired
    private ResortRepository resortRepository;

    @GetMapping("/resorts")
    public String getResorts(Model model) {
        model.addAttribute("resorts", resortRepository.findAll());
        return "resorts";
    }

    @GetMapping("/resorts/add")
    public String addResort(Model model) {
        model.addAttribute("resort", new Resort());
        return "resort_add";
    }

    @PostMapping("/resorts/add")
    public String addResort(@ModelAttribute Resort resort) {
        resortRepository.save(resort);
        return "redirect:/resorts";
    }

}
