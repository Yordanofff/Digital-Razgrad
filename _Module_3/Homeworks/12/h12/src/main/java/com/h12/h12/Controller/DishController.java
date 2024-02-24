package com.h12.h12.Controller;

import com.h12.h12.Entity.Dish;
import com.h12.h12.Entity.DishCategory;
import com.h12.h12.Repository.DishCategoryRepository;
import com.h12.h12.Repository.DishRepository;
import com.h12.h12.Repository.ProductCategoryRepository;
import com.h12.h12.Repository.ProductRepository;
import com.h12.h12.Service.DishService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class DishController {

    private final DishRepository dishRepository;
    private final DishCategoryRepository dishCategoryRepository;
    private final ProductRepository productRepository;
    private final DishService dishService;

    public DishController(DishRepository dishRepository,
                          DishCategoryRepository dishCategoryRepository,
                          ProductRepository productRepository,
                          DishService dishService
    ) {
        this.dishRepository = dishRepository;
        this.dishCategoryRepository = dishCategoryRepository;
        this.productRepository = productRepository;
        this.dishService = dishService;

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
        return dishService.saveDish(dish, bindingResult, model);
    }

    @PostMapping("/dishes/del")
    public String deleteDish(@RequestParam Long dishId, Model model) {
        dishRepository.deleteById(dishId);
        return "redirect:/dishes";
    }

    @GetMapping("/dish_categories/edit/{id}")
    public String editDishCategory(@PathVariable Long id, Model model) {
        Optional<DishCategory> optionalDishCategory = dishCategoryRepository.findById(id);
        if (optionalDishCategory.isPresent()) {
            model.addAttribute("dishCategory", optionalDishCategory.get());
            return "edit_dish_category";
        } else {
            return "redirect:/dish_categories";
        }
    }

    @PostMapping("/dish_categories/update")
    public String updateDishCategory(@Valid @ModelAttribute DishCategory dishCategory,
                                     BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "edit_dish_category";
        }
        dishCategoryRepository.save(dishCategory);
        return "redirect:/dish_categories";
    }

}
