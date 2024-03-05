package com.h12.h12;

import com.h12.h12.Entity.DishCategory;
import com.h12.h12.Entity.ProductCategory;
import com.h12.h12.Repository.DishCategoryRepository;
import com.h12.h12.Repository.ProductCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInit implements ApplicationRunner {
    final private DishCategoryRepository dishCategoryRepository;
    final private ProductCategoryRepository productCategoryRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (dishCategoryRepository.count() == 0) {
            dishCategoryRepository.save(new DishCategory("Starter"));
            dishCategoryRepository.save(new DishCategory("Salad"));
            dishCategoryRepository.save(new DishCategory("Main"));
            dishCategoryRepository.save(new DishCategory("Dessert"));
            dishCategoryRepository.save(new DishCategory("Soup"));
        }

        if (productCategoryRepository.count() == 0) {
            productCategoryRepository.save(new ProductCategory("Vegetable"));
            productCategoryRepository.save(new ProductCategory("Fruit"));
            productCategoryRepository.save(new ProductCategory("Seasoning"));
        }
    }
}
