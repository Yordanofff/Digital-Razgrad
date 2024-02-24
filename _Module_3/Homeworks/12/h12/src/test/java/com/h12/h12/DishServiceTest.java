package com.h12.h12;

import com.h12.h12.Entity.Dish;
import com.h12.h12.Repository.DishCategoryRepository;
import com.h12.h12.Repository.DishRepository;
import com.h12.h12.Repository.ProductRepository;
import com.h12.h12.Service.DishService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DishServiceTest {

    @InjectMocks
    DishService dishService;

    @Mock
    DishCategoryRepository dishCategoryRepository;

    @Mock
    ProductRepository productRepository;

    @Mock
    DishRepository dishRepository;

    @Mock
    BindingResult bindingResult;

    @Mock
    Model model;

    @Test
    public void dishServiceSaveDishTestValid() {
        //HAVING
        when(bindingResult.hasErrors()).thenReturn(true);
        Dish dish = new Dish();
        //WHEN

        dishService.saveDish(dish, bindingResult, model);

        //THEN
        verify(model, times(2)).addAttribute(anyString(),any());
        verify(dishRepository, never()).save(any());
    }

    @Test
    public void dishServiceSaveDishTestNotValid() {
        //HAVING
        when(bindingResult.hasErrors()).thenReturn(false);
        Dish dish = new Dish();
        //WHEN

        dishService.saveDish(dish, bindingResult, model);

        //THEN
        verify(model, never()).addAttribute(anyString(),any());
        verify(dishRepository, times(1)).save(any());
    }
}
