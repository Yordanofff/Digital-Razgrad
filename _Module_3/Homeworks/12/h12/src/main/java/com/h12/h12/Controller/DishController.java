package com.h12.h12.Controller;

import com.h12.h12.Entity.Dish;
import com.h12.h12.Repository.DishCategoryRepository;
import com.h12.h12.Repository.DishRepository;
import com.h12.h12.Repository.ProductCategoryRepository;
import com.h12.h12.Repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class DishController {

    private final DishRepository dishRepository;
    private final DishCategoryRepository dishCategoryRepository;
    private final ProductRepository productRepository;

    public DishController(DishRepository dishRepository,
                          DishCategoryRepository dishCategoryRepository,
                          ProductRepository productRepository) {
        this.dishRepository = dishRepository;
        this.dishCategoryRepository = dishCategoryRepository;
        this.productRepository = productRepository;
    }

    @GetMapping("/dishes")
    String getAllDishes(Model model) {
        model.addAttribute("all_dishes", dishRepository.findAll());
        return "dishes";
    }

    @GetMapping("/dishes/add")
    String addDish(Model model) {
        model.addAttribute("dish", new Dish());
        model.addAttribute("all_categories", dishCategoryRepository.findAll());
        model.addAttribute("all_products", productRepository.findAll());
        return "dishes_add";
    }

    @PostMapping("/dishes/save")
    String saveDish(@Valid @ModelAttribute Dish dish, BindingResult bindingResult, Model model) {
        System.out.println(dish);
        if (bindingResult.hasErrors()) {
            model.addAttribute("all_categories", dishCategoryRepository.findAll());
            model.addAttribute("all_products", productRepository.findAll());
            return "dishes_add";
        }

        // No need to check if the dish is already in the DB. There can be multiple recipes of the same dish.

        dishRepository.save(dish);
        return "redirect:/dishes";
    }
}
