package com.h12.h12;

import com.h12.h12.Entity.Dish;
import com.h12.h12.Entity.DishCategory;
import com.h12.h12.Entity.Product;
import com.h12.h12.Entity.ProductCategory;
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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    void dishServiceSaveDishTestValid() {
        //GIVEN
        when(bindingResult.hasErrors()).thenReturn(true);
        Dish dish = new Dish();
        //WHEN

        dishService.saveDish(dish, bindingResult, model);

        //THEN
        verify(model, times(2)).addAttribute(anyString(), any());
        verify(dishRepository, never()).save(any());
    }

    @Test
    void dishServiceSaveDishTestNotValid() {
        //GIVEN
        when(bindingResult.hasErrors()).thenReturn(false);
        Dish dish = new Dish();
        //WHEN

        dishService.saveDish(dish, bindingResult, model);

        //THEN
        verify(model, never()).addAttribute(anyString(), any());
        verify(dishRepository, times(1)).save(any());
    }

    @Test
    void getAllDishesTest() {
        //GIVEN
        List<Dish> dishesList = new ArrayList<>();
        dishesList.add(new Dish());
        when(dishRepository.findAll()).thenReturn(dishesList);

        //WHEN
        List<Dish> result = dishRepository.findAll();

        //THEN
        assertEquals(1, result.size());
    }

    @Test
    void deleteDishTest() {
        //GIVEN
        long testId = 1L;
        //WHEN
        dishService.deleteDish(testId);
        //THEN
        verify(dishRepository, times(1)).deleteById(testId);
    }

    @Test
    void addDishTest() {
        //GIVEN
        //WHEN
        dishService.addDish(model);
        //THEN
        verify(dishCategoryRepository, times(1)).findAll();
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void calculateDishRatingDefault() {
        //GIVEN
        Dish dish = new Dish();

        //WHEN
        int score = dishService.calculateDishRating(dish);

        //THEN
        assertEquals(3, score);
    }

    @Test
    void calculateDishRatingSoupOneVegetable() {
        //GIVEN
        Dish dish = new Dish();
        DishCategory soup = new DishCategory("Soup");
        dish.setDishCategory(soup);

        Product vegProduct = new Product();
        ProductCategory vegCategory = new ProductCategory("Vegetable");
        vegProduct.setProductCategory(vegCategory);

        Set<Product> products = new HashSet<>();
        products.add(vegProduct);

        dish.setProducts(products);

        //WHEN
        int score = dishService.calculateDishRating(dish);

        //THEN
        assertEquals(4, score);
    }

    @Test
    void calculateDishRatingSoupTwoVegetables() {
        //GIVEN
        Dish dish = new Dish();
        DishCategory soup = new DishCategory("Soup");
        dish.setDishCategory(soup);

        Product vegProduct = new Product();
        Product vegProduct2 = new Product();

        ProductCategory vegCategory = new ProductCategory("Vegetable");

        vegProduct.setProductCategory(vegCategory);
        vegProduct2.setProductCategory(vegCategory);

        Set<Product> products = new HashSet<>();
        products.add(vegProduct);
        products.add(vegProduct2);

        dish.setProducts(products);

        //WHEN
        int score = dishService.calculateDishRating(dish);

        //THEN
        assertEquals(5, score);
    }

    @Test
    void calculateDishRatingSoupOneFruit() {
        //GIVEN
        Dish dish = new Dish();
        DishCategory soup = new DishCategory("Soup");
        dish.setDishCategory(soup);

        Product fruitProduct = new Product();
        ProductCategory fruitCategory = new ProductCategory("Fruit");
        fruitProduct.setProductCategory(fruitCategory);

        Set<Product> products = new HashSet<>();
        products.add(fruitProduct);

        dish.setProducts(products);

        //WHEN
        int score = dishService.calculateDishRating(dish);

        //THEN
        assertEquals(1, score);
    }

    @Test
    void calculateDishRatingSoupOneVegetableOneFruit() {
        //GIVEN
        Dish dish = new Dish();
        DishCategory soup = new DishCategory("Soup");
        dish.setDishCategory(soup);

        Product fruitProduct = new Product();
        Product vegProduct = new Product();

        ProductCategory fruitCategory = new ProductCategory("Fruit");
        ProductCategory vegCategory = new ProductCategory("Vegetable");

        fruitProduct.setProductCategory(fruitCategory);
        vegProduct.setProductCategory(vegCategory);

        Set<Product> products = new HashSet<>();
        products.add(fruitProduct);
        products.add(vegProduct);

        dish.setProducts(products);

        //WHEN
        int score = dishService.calculateDishRating(dish);

        //THEN
        assertEquals(2, score);
    }

    @Test
    void calculateDishRatingSaladOneNonVegetableOrFruit() {
        //GIVEN
        Dish dish = new Dish();
        DishCategory soup = new DishCategory("Salad");
        dish.setDishCategory(soup);

        Product vegProduct = new Product();
        ProductCategory vegCategory = new ProductCategory("SomethingElse");
        vegProduct.setProductCategory(vegCategory);

        Set<Product> products = new HashSet<>();
        products.add(vegProduct);

        dish.setProducts(products);

        //WHEN
        int score = dishService.calculateDishRating(dish);

        //THEN
        assertEquals(3, score);
    }

    @Test
    void calculateDishRatingSaladOneVegetable() {
        //GIVEN
        Dish dish = new Dish();
        DishCategory soup = new DishCategory("Salad");
        dish.setDishCategory(soup);

        Product vegProduct = new Product();
        ProductCategory vegCategory = new ProductCategory("Vegetable");
        vegProduct.setProductCategory(vegCategory);

        Set<Product> products = new HashSet<>();
        products.add(vegProduct);

        dish.setProducts(products);

        //WHEN
        int score = dishService.calculateDishRating(dish);

        //THEN
        assertEquals(4, score);
    }

    @Test
    void calculateDishRatingSaladOneFruit() {
        //GIVEN
        Dish dish = new Dish();
        DishCategory soup = new DishCategory("Salad");
        dish.setDishCategory(soup);

        Product fruitProduct = new Product();
        ProductCategory fruitCategory = new ProductCategory("Fruit");
        fruitProduct.setProductCategory(fruitCategory);

        Set<Product> products = new HashSet<>();
        products.add(fruitProduct);

        dish.setProducts(products);

        //WHEN
        int score = dishService.calculateDishRating(dish);

        //THEN
        assertEquals(4, score);
    }

    @Test
    void calculateDishRatingSaladOneVegetableOneFruit() {
        //GIVEN
//        Dish dish = new Dish().setDishCategory(new DishCategory("Salad"));
        Dish dish = Dish.builder().dishCategory(new DishCategory("Salad")).build();

        Set<Product> products = new HashSet<>();
        products.add(Product.builder().productCategory(new ProductCategory("Fruit")).build());

//        products.add(new Product().setProductCategory(new ProductCategory("Vegetable")));
        products.add(Product.builder().productCategory(new ProductCategory("Vegetable")).build());

        dish.setProducts(products);

        //WHEN
        int score = dishService.calculateDishRating(dish);

        //THEN
        assertEquals(5, score);
    }


    @Test
    void calculateDishRatingDessertOneFruit() {
        //GIVEN
        Dish dish = Dish.builder().dishCategory(new DishCategory("Dessert")).build();

        Set<Product> products = new HashSet<>();
        products.add(Product.builder().productCategory(new ProductCategory("Fruit")).build());

        dish.setProducts(products);

        //WHEN
        int score = dishService.calculateDishRating(dish);

        //THEN
        assertEquals(4, score);
    }

    @Test
    void calculateDishRatingDessertOneVegetable() {
        //GIVEN
        Dish dish = Dish.builder().dishCategory(new DishCategory("Dessert")).build();

        Set<Product> products = new HashSet<>();
        products.add(Product.builder().productCategory(new ProductCategory("Vegetable")).build());

        dish.setProducts(products);

        //WHEN
        int score = dishService.calculateDishRating(dish);

        //THEN
        assertEquals(1, score);
    }

    @Test
    void calculateDishRatingDessertOneOther() {
        //GIVEN
        Dish dish = Dish.builder().dishCategory(new DishCategory("Dessert")).build();

        Set<Product> products = new HashSet<>();
        products.add(Product.builder().productCategory(new ProductCategory("Other")).build());

        dish.setProducts(products);

        //WHEN
        int score = dishService.calculateDishRating(dish);

        //THEN
        assertEquals(3, score);
    }

    @Test
    void calculateDishRatingDessertNoItems() {
        //GIVEN
        Dish dish = Dish.builder().dishCategory(new DishCategory("Dessert")).build();

        //WHEN
        int score = dishService.calculateDishRating(dish);

        //THEN
        assertEquals(3, score);
    }

    @Test
    void calculateDishRatingDessertWithChocolate() {
        //GIVEN
        Dish dish = Dish.builder().dishCategory(new DishCategory("Dessert")).build();

        Set<Product> products = new HashSet<>();
        products.add(Product.builder()
                .name("Chocolate")
                .productCategory(new ProductCategory("Other"))
                .build());

        dish.setProducts(products);

        //WHEN
        int score = dishService.calculateDishRating(dish);

        //THEN
        assertEquals(6, score);
    }

    @Test
    void calculateDishRatingMainOneVegetable() {
        //GIVEN
        Dish dish = Dish.builder().dishCategory(new DishCategory("Main")).build();

        Set<Product> products = new HashSet<>();
        products.add(Product.builder().productCategory(new ProductCategory("Vegetable")).build());

        dish.setProducts(products);

        //WHEN
        int score = dishService.calculateDishRating(dish);

        //THEN
        assertEquals(5, score);
    }

    @Test
    void calculateDishRatingMainOneOther() {
        //GIVEN
        Dish dish = Dish.builder().dishCategory(new DishCategory("Main")).build();

        Set<Product> products = new HashSet<>();
        products.add(Product.builder().productCategory(new ProductCategory("Other")).build());

        dish.setProducts(products);

        //WHEN
        int score = dishService.calculateDishRating(dish);

        //THEN
        assertEquals(3, score);
    }

//    @Test
//    void addDishTest() {
//        //GIVEN
//        //WHEN
//        //THEN
//    }
}
