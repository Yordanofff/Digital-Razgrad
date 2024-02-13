package com.holidays.h10.Resort;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CountryController {
    private final CountryRepository countryRepository;

    public CountryController(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @GetMapping("/countries")
    public String getCountries(Model model) {
        model.addAttribute("counties", countryRepository.findAll());
        return "countries";
    }

    @GetMapping("/countries/add")
    public String addCountry(Model model) {
        model.addAttribute("country", new Country());
        return "countries_add";
    }

    @PostMapping("/countries/add")
    public String addCountry(@ModelAttribute Country country) {
        countryRepository.save(country);
        return "redirect:/countries";
    }
}
