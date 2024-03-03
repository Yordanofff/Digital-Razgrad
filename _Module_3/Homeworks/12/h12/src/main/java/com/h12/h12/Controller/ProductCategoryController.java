package com.h12.h12.Controller;

import com.h12.h12.Entity.ProductCategory;
import com.h12.h12.Service.ProductCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
@RequestMapping("/product_categories")
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    // Това работи
    @GetMapping()
    String getCategories(Model model) {
        return productCategoryService.getCategories(model);
    }

    @GetMapping("/add")
    String showAddProductCategoryForm(Model model) {
        return productCategoryService.showAddProductCategoryForm(model);
    }

    @PostMapping("/save")
    String addCategory(@Valid @ModelAttribute ProductCategory productCategory, BindingResult bindingResult, Model model) {
        return productCategoryService.addCategory(productCategory, bindingResult, model);
    }

    @PostMapping("/del")
    String deleteProductCategory(@RequestParam Long id) {
        return productCategoryService.deleteProductCategory(id);
    }

    @GetMapping("/edit/{id}")
    String editProductCategory(@PathVariable Long id, Model model) {
        return productCategoryService.editProductCategory(id, model);
    }

    @PostMapping("/update")
    String updateProductCategory(@Valid @ModelAttribute ProductCategory productCategory, BindingResult bindingResult) {
        return productCategoryService.updateProductCategory(productCategory, bindingResult);
    }

}
