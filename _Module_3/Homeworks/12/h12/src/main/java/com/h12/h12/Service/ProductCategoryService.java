package com.h12.h12.Service;

import com.h12.h12.Entity.ProductCategory;
import com.h12.h12.Repository.ProductCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductCategoryService {

    private ProductCategoryRepository productCategoryRepository;

    public String getCategories(Model model) {
        model.addAttribute("product_categories", productCategoryRepository.findAll());
        return "product_categories";
    }

    public String showAddProductCategoryForm(Model model) {
        model.addAttribute("productCategory", new ProductCategory());
        return "product_categories_add";
    }

    public String addCategory(ProductCategory productCategory, BindingResult bindingResult, Model model) {
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

    public String deleteProductCategory(Long id) {
        productCategoryRepository.deleteById(id);
        return "redirect:/product_categories";
    }

    public String editProductCategory(Long id, Model model) {

        Optional<ProductCategory> optionalProductCategory = productCategoryRepository.findById(id);

        if (optionalProductCategory.isPresent()) {
            model.addAttribute("productCategory", optionalProductCategory.get());
            return "edit_product_category";
        } else {
            return "redirect:/product_categories";
        }
    }

    public String updateProductCategory(ProductCategory productCategory, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit_product_category";
        }
        productCategoryRepository.save(productCategory);
        return "redirect:/product_categories";
    }

}
