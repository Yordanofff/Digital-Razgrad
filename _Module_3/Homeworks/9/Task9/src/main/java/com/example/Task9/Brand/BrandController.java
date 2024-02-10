package com.example.Task9.Brand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
//@RequestMapping("/brand")
public class BrandController {
    private final BrandRepository brandRepository;
    private final BrandService brandService;

    public BrandController(BrandRepository brandRepository,
                           BrandService brandService) {
        this.brandRepository = brandRepository;
        this.brandService = brandService;
    }

    @GetMapping("/brands")
    Iterable<Brand> getBrands() {
        return brandRepository.findAll();
    }

    @PostMapping("/brands")
    @ResponseBody
    Brand addBrand(@RequestParam String name,
                   @RequestParam int year,
                   @RequestParam(required = false) String countryName) {

        return brandService.addBrand(name, year, countryName);

    }
}
