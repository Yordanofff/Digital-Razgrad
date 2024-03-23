package com.h19.h19.Entity;

import com.h19.h19.Repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class FakeProductsController {

    private final CategoryRepository categoryRepository;

    public FakeProductsController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @PostMapping("/api/categories")
    public ResponseEntity<?> updateAllCategories() {
        String url = "https://fakestoreapi.com/products/categories";
        RestTemplate restTemplate = new RestTemplate();

        String[] categories = restTemplate.getForObject(url, String[].class);

        if (categories == null){
            return ResponseEntity.notFound().build();
        }
        for (String c: categories) {
            if (categoryRepository.findByName(c).isEmpty()){
                Category category = new Category();
                category.setName(c);
                categoryRepository.save(category);
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body("Categories saved successfully!");
    }

    @GetMapping("/api/categories")
    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }
}
