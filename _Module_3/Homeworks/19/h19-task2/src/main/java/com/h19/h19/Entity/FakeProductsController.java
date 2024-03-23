package com.h19.h19.Entity;

import com.h19.h19.dto.CategoriesDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class FakeProductsController {

    @GetMapping("/api/categories")
    public ResponseEntity<CategoriesDto> getFakeCategories() {
        String url = "https://fakestoreapi.com/products/categories";
        RestTemplate restTemplate = new RestTemplate();

        String[] categories = restTemplate.getForObject(url, String[].class);

        if (categories == null){
            return ResponseEntity.notFound().build();
        }
        for (String c: categories) {
            Category category = new Category();
            category.setName(c);
        }

    }
}
