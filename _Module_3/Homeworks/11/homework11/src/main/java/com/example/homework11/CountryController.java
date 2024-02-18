package com.example.homework11;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/countries")
public class CountryController {

    private final CountryRepository countryRepository;

    public CountryController(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @GetMapping
    public String getCountries(Model model) {
        model.addAttribute("countries", countryRepository.findAll());
        model.addAttribute("country", new Country());
        return "countries";
    }

    @PostMapping("/add")
    public String addCountry(@ModelAttribute Country country) {
        countryRepository.save(country);
        return "redirect:/countries";
    }


    @PostMapping("/del")
    public String deleteCountry(@RequestParam String countryName) {
        Optional<Country> optionalCountry = countryRepository.findByName(countryName);
        if (optionalCountry.isPresent()) {
            countryRepository.delete(optionalCountry.get());
            return "redirect:/countries";
        } else {
            throw new IllegalStateException("Country not in the DB.");
        }
    }
}
