package com.example.Task9.Brand;

import com.example.Task9.Brand.Country.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
//@RequestMapping("/brand")
public class BrandController {
    private final BrandRepository brandRepository;
    private final BrandService brandService;

    @Autowired
    CountryRepository countryRepository;

    public BrandController(BrandRepository brandRepository,
                           BrandService brandService) {
        this.brandRepository = brandRepository;
        this.brandService = brandService;
    }

    @GetMapping("/brands/get/json")
    @ResponseBody
    Iterable<Brand> getBrands() {
        return brandRepository.findAll();
    }

    @PostMapping("/brands/add/manual")
    @ResponseBody
    Brand addBrand(@RequestParam String name,
                   @RequestParam int year,
                   @RequestParam(required = false) String countryName) {

        return brandService.addBrand(name, year, countryName);
    }

    @GetMapping("/brands/get")
    String getBrands(Model model){
        Iterable<Brand> brandsIterable = brandRepository.findAll();
        List<Brand> brands = (List<Brand>) brandsIterable;
        model.addAttribute("brands", brands);
        return "brands";
    }

    @GetMapping("/brands/add")
    public String addBrand(Model model) {
        model.addAttribute("brand", new Brand());
        model.addAttribute("countries", countryRepository.findAll());
        return "new_brand_form";
    }

    @PostMapping("/brands/submit")
    public String submitBrand(@ModelAttribute Brand brand) {
        brandRepository.save(brand);
        return "redirect:/brands/get";
    }


}
