package com.h12.h12.Service;

import com.h12.h12.Entity.Dish;
import com.h12.h12.Entity.Product;
import com.h12.h12.Repository.DishCategoryRepository;
import com.h12.h12.Repository.DishRepository;
import com.h12.h12.Repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.List;


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

    public String deleteDish(Long id) {
        dishRepository.deleteById(id);
        return "redirect:/dishes";
    }

    public String addDish(Model model) {
        model.addAttribute("dish", new Dish());
        model.addAttribute("all_categories", dishCategoryRepository.findAll());
        model.addAttribute("all_products", productRepository.findAll());
        return "dishes_add";
    }

    public String getAllDishes(Model model) {
        List<Dish> allDishes = dishRepository.findAll();
        for (Dish dish : allDishes) {
            dish.setRating(calculateDishRating(dish));
        }
        model.addAttribute("all_dishes", allDishes);
        return "dishes";
    }

    public int calculateDishRating(Dish dish) {
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
