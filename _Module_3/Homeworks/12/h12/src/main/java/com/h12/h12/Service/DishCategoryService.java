package com.h12.h12.Service;

import com.h12.h12.Entity.DishCategory;
import com.h12.h12.Repository.DishCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DishCategoryService {

    private final DishCategoryRepository dishCategoryRepository;

    public String editDishCategory(Long id, Model model) {
        Optional<DishCategory> optionalDishCategory = dishCategoryRepository.findById(id);
        if (optionalDishCategory.isPresent()) {
            model.addAttribute("dishCategory", optionalDishCategory.get());
            return "edit_dish_category";
        } else {
            return "redirect:/dish_categories";
        }
    }

    public String updateDishCategory(DishCategory dishCategory, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit_dish_category";
        }
        dishCategoryRepository.save(dishCategory);
        return "redirect:/dish_categories";
    }

    public String deleteDishCategory(Long id) {
        dishCategoryRepository.deleteById(id);
        return "redirect:/dish_categories";
    }

    public String saveDishCategories(DishCategory dishCategory, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("dishCategories", dishCategoryRepository.findAll());
            model.addAttribute("category", dishCategory);
            return "dish_categories";
        }

        dishCategoryRepository.save(dishCategory);
        return "redirect:/dish_categories";
    }

    public String getDishCategories(Model model) {
        model.addAttribute("dishCategories", dishCategoryRepository.findAll());
        model.addAttribute("category", new DishCategory());
        return "dish_categories";
    }
}
