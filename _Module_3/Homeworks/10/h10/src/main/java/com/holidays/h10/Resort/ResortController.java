package com.holidays.h10.Resort;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ResortController {
    @Autowired
    private ResortRepository resortRepository;

    @GetMapping("/resorts")
    public String getResorts(Model model) {
        model.addAttribute("resorts", resortRepository.findAll());
        return "get_resorts";
    }

}
