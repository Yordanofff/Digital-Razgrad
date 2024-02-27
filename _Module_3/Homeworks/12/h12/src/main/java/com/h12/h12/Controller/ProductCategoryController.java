package com.h12.h12.Controller;

import com.h12.h12.Entity.DishCategory;
import com.h12.h12.Entity.ProductCategory;
import com.h12.h12.Repository.ProductCategoryRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product_categories")
public class ProductCategoryController {

    private final ProductCategoryRepository productCategoryRepository;

    // Това работи
    @GetMapping()
    String getCategories(Model model) {
        model.addAttribute("product_categories", productCategoryRepository.findAll());
        return "product_categories";
    }

    @GetMapping("/add")
    public String showAddProductCategoryForm(Model model) {
        model.addAttribute("productCategory", new ProductCategory());
        return "product_categories_add";
    }

    @PostMapping("/save")
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

    @PostMapping("/del")
    public String deleteProductCategory(@RequestParam Long id) {
        productCategoryRepository.deleteById(id);
        return "redirect:/product_categories";
    }

    @GetMapping("/edit/{id}")
    public String editProductCategory(@PathVariable Long id, Model model) {

        Optional<ProductCategory> optionalProductCategory = productCategoryRepository.findById(id);

        if (optionalProductCategory.isPresent()) {
            model.addAttribute("productCategory", optionalProductCategory.get());
            return "edit_product_category";
        } else {
            return "redirect:/product_categories";
        }
    }

    @PostMapping("/update")
    public String updateProductCategory(@Valid @ModelAttribute ProductCategory productCategory, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit_product_category";
        }
        productCategoryRepository.save(productCategory);
        return "redirect:/product_categories";
    }

}
