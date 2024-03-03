package com.h12.h12.Service;

import com.h12.h12.Entity.Product;
import com.h12.h12.Repository.DishRepository;
import com.h12.h12.Repository.ProductCategoryRepository;
import com.h12.h12.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final DishRepository dishRepository;

    public String getAllProducts(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "products";
    }

    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", productCategoryRepository.findAll());
        model.addAttribute("all_dishes", dishRepository.findAll());
        return "products_add";
    }

    public String saveProduct(Product product, BindingResult bindingResult, Model model
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
