package com.h12.h12.Rest;

import com.h12.h12.Entity.Product;
import com.h12.h12.Entity.ProductCategory;
import com.h12.h12.Repository.ProductRepository;
import com.h12.h12.dto.ProductDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api")
public class ProductControllerREST {
    private final ProductRepository productRepository;

    public ProductControllerREST(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/v1/products")
    @ResponseBody
    List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/v2/products")
    @ResponseBody
    List<ProductDTO> getAllProductsDto() {
        List<Product> productList = productRepository.findAll();
        List<ProductDTO> returnList = new ArrayList<>();
        for (Product p : productList) {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setName(p.getName());
            productDTO.setCategory(p.getProductCategory().getName());
            returnList.add(productDTO);
        }
        return returnList;
    }
}
