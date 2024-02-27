package com.h12.h12.Controller;

import com.h12.h12.Entity.DishCategory;
import com.h12.h12.Repository.DishCategoryRepository;
import com.h12.h12.Service.DishCategoryService;
import com.h12.h12.Service.DishService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class DishCategoryController {

    private final DishCategoryRepository dishCategoryRepository;
    private final DishService dishService;
    private final DishCategoryService dishCategoryService;

    // Това не работи
    @GetMapping("/dish_categories")
    public String getDishCategories(Model model) {
        model.addAttribute("dishCategories", dishCategoryRepository.findAll());
        model.addAttribute("category", new DishCategory());
        return "dish_categories";
    }

    @PostMapping("/dish_categories/save")
    public String saveDishCategories(@Valid @ModelAttribute DishCategory dishCategory,
                                     BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
        model.addAttribute("dishCategories", dishCategoryRepository.findAll());
            model.addAttribute("category", dishCategory);
            return "dish_categories";
        }

        dishCategoryRepository.save(dishCategory);
        return "redirect:/dish_categories";
    }

    @PostMapping("/dish_categories/del")
    public String deleteDishCategory(@RequestParam Long id) {
        dishCategoryRepository.deleteById(id);
        return "redirect:/dish_categories";
    }

    @GetMapping("/dish_categories/edit/{id}")
    public String editDishCategory(@PathVariable Long id, Model model) {
        return dishCategoryService.editDishCategory(id, model);
    }

    @PostMapping("/dish_categories/update")
    public String updateDishCategory(@Valid @ModelAttribute DishCategory dishCategory, BindingResult bindingResult) {
        return dishCategoryService.updateDishCategory(dishCategory, bindingResult);
    }

}



