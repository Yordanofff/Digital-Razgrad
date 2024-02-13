package com.holidays.h10;

import com.holidays.h10.Resort.Resort;
import com.holidays.h10.Resort.ResortRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HotelController {

    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private ResortRepository resortRepository;

    @GetMapping("/hotels")
    public String getHotel(Model model) {
        model.addAttribute("hotels",hotelRepository.findAll());
        return "hotels";
    }

    @GetMapping("/hotels/add")
    public String addHotel(Model model){
        model.addAttribute("hotel", new Hotel());
        model.addAttribute("resorts", resortRepository.findAll());
        return "hotel_add";
    }

    @PostMapping("/hotels/save")
    public String saveHotel(@ModelAttribute Hotel hotel){
        hotelRepository.save(hotel);
        return "redirect:/hotels";
    }
}
