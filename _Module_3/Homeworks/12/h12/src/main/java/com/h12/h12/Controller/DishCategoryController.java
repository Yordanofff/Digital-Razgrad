package com.h12.h12.Controller;

import com.h12.h12.Entity.DishCategory;
import com.h12.h12.Entity.ProductCategory;
import com.h12.h12.Repository.DishCategoryRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DishCategoryController {

    private final DishCategoryRepository dishCategoryRepository;

    public DishCategoryController(DishCategoryRepository dishCategoryRepository) {
        this.dishCategoryRepository = dishCategoryRepository;
    }

    @GetMapping("/dish/categories/view")
    public String getDishCategories(Model model) {
        model.addAttribute("dishCategories", dishCategoryRepository.findAll());
        model.addAttribute("category", new DishCategory());
        return "dish_categories";
    }

    @PostMapping("/dish/categories/save")
    public String getDishCategories(@Valid @ModelAttribute DishCategory dishCategory, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.toString()); // Add this to debug
            model.addAttribute("category", dishCategory);
            return "dish_categories";
        }


//        if (bindingResult.hasErrors()) {
//            model.addAttribute("dishCategories", dishCategoryRepository.findAll());
////            model.addAttribute("category", new DishCategory());
//            model.addAttribute("category", dishCategory);
//            return "dish_categories";
//        }
        dishCategoryRepository.save(dishCategory);
        return "redirect:/dish/categories/view";
    }
}
