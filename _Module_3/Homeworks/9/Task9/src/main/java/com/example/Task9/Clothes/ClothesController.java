package com.example.Task9.Clothes;

import com.example.Task9.Brand.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ClothesController {
    @Autowired
    private ClothesRepository clothesRepository;
    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ClothesService clothesService;

    @GetMapping("/clothes")
    Iterable<Clothes> getClothes() {
        return clothesRepository.findAll();
    }

    @PostMapping("/clothes")
    Clothes addCloth(@RequestParam String name,
                     @RequestParam String brandName,
                     @RequestParam Long sizeId,
                     @RequestParam String typeName,
                     @RequestParam Double price,
                     @RequestParam(required = false) int quantity) {
        return clothesService.addCloth(name, brandName, sizeId, typeName, price, quantity);
    }
}
