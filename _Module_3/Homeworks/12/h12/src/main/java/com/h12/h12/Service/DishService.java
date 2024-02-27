package com.h12.h12.Service;

import com.h12.h12.Entity.Dish;
import com.h12.h12.Entity.Product;
import com.h12.h12.Repository.DishCategoryRepository;
import com.h12.h12.Repository.DishRepository;
import com.h12.h12.Repository.ProductRepository;
import jakarta.transaction.Transactional;
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

    @Transactional
    public Dish saveDishWithRating(Dish dish) {
        int rating = getDishRating(dish);
        dish.setRating(rating);
        return dishRepository.save(dish);
    }

    public int getDishRating(Dish dish) {
        int score = 3;
        for (Product product : dish.getProducts()) {
            if (dish.getDishCategory().getName().equalsIgnoreCase("soup")) {
                score += getSoupScore(product);
            } else if (dish.getDishCategory().getName().equalsIgnoreCase("salad")) {
                score += getSaladScore(product);
            } else if (dish.getDishCategory().getName().equalsIgnoreCase("desert")) {
                score += getDesertScore(product);
            } else if (dish.getDishCategory().getName().equalsIgnoreCase("main")) {
                score += getMainScore(product);
            }
        }
        return score;
    }

    private int getSoupScore(Product product) {
        int score = 0;
        if (product.getProductCategory().getName().equalsIgnoreCase("vegetable")) {
            score += 1;
        } else if ((product.getProductCategory().getName().equalsIgnoreCase("fruit"))) {
            score -= 2;
        }
        return score;
    }

    private int getSaladScore(Product product) {
        int score = 0;
        if ((product.getProductCategory().getName().equalsIgnoreCase("fruit")) ||
                product.getProductCategory().getName().equalsIgnoreCase("vegetable")) {
            score += 1;
        }
        return score;
    }

    private int getDesertScore(Product product) {
        int score = 0;
        if (product.getProductCategory().getName().equalsIgnoreCase("fruit")) {
            score += 1;
        } else if (product.getProductCategory().getName().equalsIgnoreCase("vegetable")) {
            score -= 2;
        }
        if (product.getName().equalsIgnoreCase("cholocate")) {
            score += 3;
        }
        return score;
    }

    private int getMainScore(Product product) {
        int score = 0;
        if (product.getProductCategory().getName().equalsIgnoreCase("vegetable")) {
            score += 2;
        }
        return score;
    }
}
