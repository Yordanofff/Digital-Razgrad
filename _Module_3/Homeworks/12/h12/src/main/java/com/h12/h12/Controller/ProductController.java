package com.h12.h12.Controller;

import com.h12.h12.Entity.Product;
import com.h12.h12.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    String getAllProducts(Model model) {
        return productService.getAllProducts(model);
    }

    @GetMapping("/products/add")
    String addProduct(Model model) {
        return productService.addProduct(model);
    }

    @PostMapping("/products/save")
    String saveProduct(@Valid @ModelAttribute Product product, BindingResult bindingResult, Model model) {
        return productService.saveProduct(product, bindingResult, model);
    }

}
