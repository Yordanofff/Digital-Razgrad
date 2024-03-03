package com.h12.h12.Controller;

import com.h12.h12.Entity.DishCategory;
import com.h12.h12.Service.DishCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class DishCategoryController {

    private final DishCategoryService dishCategoryService;

    // Това не работи
    @GetMapping("/dish_categories")
    public String getDishCategories(Model model) {
        return dishCategoryService.getDishCategories(model);
    }

    @PostMapping("/dish_categories/save")
    public String saveDishCategories(@Valid @ModelAttribute DishCategory dishCategory,
                                     BindingResult bindingResult, Model model) {

        return dishCategoryService.saveDishCategories(dishCategory, bindingResult, model);
    }

    @PostMapping("/dish_categories/del")
    public String deleteDishCategory(@RequestParam Long id) {
        return dishCategoryService.deleteDishCategory(id);
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



