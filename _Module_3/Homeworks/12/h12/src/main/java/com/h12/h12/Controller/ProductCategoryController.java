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
@RequestMapping("/products/categories")
public class ProductCategoryController {

    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategoryController(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    @GetMapping()
    String getCategories(Model model) {
        model.addAttribute("product_categories", productCategoryRepository.findAll());
        model.addAttribute("productCategory", new ProductCategory());
        return "product_categories";
    }

    //    @PostMapping("/new")
//    String addCategory(@Valid @ModelAttribute ProductCategory productCategory, BindingResult bindingResult, Model model) {
//        if (bindingResult.hasErrors()) {
//            System.out.println("huston" + bindingResult);
//            model.addAttribute("product_categories", productCategoryRepository.findAll());
//            model.addAttribute("productCategory", new ProductCategory());
//            return "product_categories";
//        }
//
//        Optional<ProductCategory> optionalProductCategory = productCategoryRepository.findByName(productCategory.getName());
//        if (optionalProductCategory.isPresent()) {
//            model.addAttribute("product_categories", productCategoryRepository.findAll());
//            model.addAttribute("productCategory", new ProductCategory());
//            model.addAttribute("not_unique_category", "The category already exist.");
//            return "product_categories";
//        }
//        productCategoryRepository.save(productCategory);
//        return "redirect:/products/categories";
//    }
    @PostMapping()
    String addCategory(@Valid @ModelAttribute ProductCategory productCategory, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println("huston " + bindingResult);

            System.out.println("Model attributes before returning view:");
            model.asMap().forEach((key, value) -> System.out.println(key + ": " + value));

            model.addAttribute("product_categories", productCategoryRepository.findAll());
            model.addAttribute("productCategory", new ProductCategory());
            return "product_categories";
        }

        Optional<ProductCategory> optionalProductCategory = productCategoryRepository.findByName(productCategory.getName());
        if (optionalProductCategory.isPresent()) {
            model.addAttribute("product_categories", productCategoryRepository.findAll());
            model.addAttribute("productCategory", new ProductCategory());
            model.addAttribute("not_unique_category", "The category already exists.");
            return "product_categories";
        }
        productCategoryRepository.save(productCategory);
        return "redirect:/products/categories";
    }


}
