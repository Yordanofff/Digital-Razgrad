package com.h12.h12.Service;

import com.h12.h12.Entity.Dish;
import com.h12.h12.Repository.DishCategoryRepository;
import com.h12.h12.Repository.DishRepository;
import com.h12.h12.Repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Service
public class DishService {

    private final DishCategoryRepository dishCategoryRepository;
    private final ProductRepository productRepository;
    private final DishRepository dishRepository;

    public DishService(DishCategoryRepository dishCategoryRepository,
                       ProductRepository productRepository,
                       DishRepository dishRepository) {
        this.dishCategoryRepository = dishCategoryRepository;
        this.productRepository = productRepository;
        this.dishRepository = dishRepository;
    }

    public String saveDish(Dish dish, BindingResult bindingResult, Model model) {
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
