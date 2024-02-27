package com.h12.h12.Controller;

import com.h12.h12.Entity.Product;
import com.h12.h12.Repository.DishRepository;
import com.h12.h12.Repository.ProductCategoryRepository;
import com.h12.h12.Repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final DishRepository dishRepository;

    public ProductController(ProductRepository productRepository,
                             ProductCategoryRepository productCategoryRepository,
                             DishRepository dishRepository) {
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.dishRepository = dishRepository;
    }

    @GetMapping("/products")
    String getAllProducts(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "products";
    }

    @GetMapping("/products/add")
    String addProduct(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", productCategoryRepository.findAll());
        model.addAttribute("all_dishes", dishRepository.findAll());
        return "products_add";
    }

    @PostMapping("/products/save")
    String saveProduct(@Valid @ModelAttribute Product product, BindingResult bindingResult, Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", productCategoryRepository.findAll());
            model.addAttribute("all_dishes", dishRepository.findAll());
            return "products_add";
        }

        Optional<Product> optionalProduct = productRepository.findByName(product.getName());
        if (optionalProduct.isPresent()) {
            model.addAttribute("categories", productCategoryRepository.findAll());
            model.addAttribute("all_dishes", dishRepository.findAll());
            model.addAttribute("product_exist", "The product \"" + product.getName() + "\" already exists.");
            return "products_add";
        }

        productRepository.save(product);

        return "redirect:/products";
    }

}
