package com.holidays.h10;

import com.holidays.h10.Resort.Country;
import com.holidays.h10.Resort.CountryRepository;
import com.holidays.h10.Resort.Resort;
import com.holidays.h10.Resort.ResortRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HotelController {

    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private ResortRepository resortRepository;
    @Autowired
    private CountryRepository countryRepository;

    @GetMapping("/hotels")
    public String getHotel(Model model) {
        model.addAttribute("hotels", hotelRepository.findAll());
        return "hotels";
    }

    @GetMapping("/hotels/add/pre")
    public String addHotel(Model model) {
        model.addAttribute("countries", countryRepository.findAll());
        return "hotel_add_pre";
    }

    @PostMapping("/hotels/add/pre")
    public String getCountryForHotel(@RequestParam Long countryId) {
        return "redirect:/hotels/add?countryId=" + countryId;
    }

    @GetMapping("/hotels/add")
    public String addHotelWithCountry(@RequestParam Long countryId, Model model) {
        Country selectedCountry = countryRepository.findById(countryId).orElseThrow(() -> new IllegalArgumentException("Invalid country ID"));
        List<Resort> resorts = resortRepository.findByCountryId(countryId);

        model.addAttribute("hotel", new Hotel());
        model.addAttribute("countries", countryRepository.findAll());
        model.addAttribute("resorts", resorts);
        return "hotel_add";
    }

    @PostMapping("/hotels/save")
    public String saveHotel(@ModelAttribute Hotel hotel) {
        hotelRepository.save(hotel);
        return "redirect:/hotels";
    }
}
