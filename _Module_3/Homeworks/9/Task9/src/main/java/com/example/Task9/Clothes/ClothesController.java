package com.example.Task9.Clothes;

import com.example.Task9.Brand.BrandRepository;
import com.example.Task9.Clothes.Size.ClothesSizeRepository;
import com.example.Task9.Clothes.Type.ClothTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class ClothesController {
    @Autowired
    private ClothesRepository clothesRepository;
    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ClothesService clothesService;
    @Autowired
    private ClothesSizeRepository clothesSizeRepository;
    @Autowired
    private ClothTypeRepository clothTypeRepository;

    @GetMapping("/clothes/get/json")
    @ResponseBody
    Iterable<Clothes> getClothesJson() {
        return clothesRepository.findAll();
    }

    @PostMapping("/clothes/add/manual")
    Clothes addCloth(@RequestParam String name,
                     @RequestParam String brandName,
                     @RequestParam Long sizeId,
                     @RequestParam String typeName,
                     @RequestParam Double price,
                     @RequestParam(required = false) int quantity) {
        return clothesService.addCloth(name, brandName, sizeId, typeName, price, quantity);
    }

    @GetMapping("/clothes/get")
    public String getClothes(Model model){
//        List<Clothes> clothesList = (List<Clothes>) clothesRepository.findAll();
        Iterable<Clothes> clothesList = clothesRepository.findAll();
        model.addAttribute("clothes", clothesList);
        return "clothes";
    }

    @GetMapping("/clothes/add")
    public String addCloth(Model model) {
        model.addAttribute("cloth", new Clothes());
        model.addAttribute("brands", brandRepository.findAll());
        model.addAttribute("sizes", clothesSizeRepository.findAll());
        model.addAttribute("types", clothTypeRepository.findAll());
        return "new_cloth_form";
    }

    @PostMapping("/clothes/submit")
    public String submitCloth(@ModelAttribute Clothes clothes){
        clothesRepository.save(clothes);
        return "redirect:/clothes/get";
    }
}
