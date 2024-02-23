package com.h12.h12.Controller;

import com.h12.h12.Entity.ProductCategory;
import com.h12.h12.Repository.ProductCategoryRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class ProductCategoryController {

    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategoryController(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    // Това работи
    @GetMapping("/product_categories")
    String getCategories(Model model) {
        model.addAttribute("product_categories", productCategoryRepository.findAll());
        return "product_categories";
    }

    @GetMapping("/product_categories/add")
    public String showAddProductCategoryForm(Model model) {
        model.addAttribute("productCategory", new ProductCategory());
        return "product_categories_add";
    }

    @PostMapping("/product_categories/save")
    String addCategory(@Valid @ModelAttribute ProductCategory productCategory,
                       BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "product_categories_add";
        }

        Optional<ProductCategory> optionalProductCategory = productCategoryRepository.findByName(productCategory.getName());
        if (optionalProductCategory.isPresent()) {
            model.addAttribute("product_categories", productCategoryRepository.findAll());
            model.addAttribute("not_unique_category", "The category already exist.");
            return "product_categories_add";
        }
        productCategoryRepository.save(productCategory);
        return "redirect:/product_categories";
    }
}
