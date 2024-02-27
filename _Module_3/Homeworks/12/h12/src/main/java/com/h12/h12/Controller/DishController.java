package com.h12.h12.Controller;

import com.h12.h12.Entity.Dish;
import com.h12.h12.Service.DishService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/dishes")
public class DishController {

    private final DishService dishService;

    @GetMapping()
    String getAllDishes(Model model) {
        return dishService.getAllDishes(model);
    }

    @GetMapping("/add")
    String addDish(Model model) {
        return dishService.addDish(model);
    }

    @PostMapping("/save")
    String saveDish(@Valid @ModelAttribute Dish dish, BindingResult bindingResult, Model model) {
        return dishService.saveDish(dish, bindingResult, model);
    }

    @PostMapping("/del")
    public String deleteDish(@RequestParam Long id) {
        return dishService.deleteDish(id);
    }
}
