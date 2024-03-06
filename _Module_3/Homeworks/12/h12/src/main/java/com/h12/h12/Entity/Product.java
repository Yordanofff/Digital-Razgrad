package com.h12.h12.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@JsonIgnoreProperties(value = {"id"})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(min=3, max=30, message = "Size must be between 3 and 30")
    @NotBlank(message = "Product name cannot be empty!")
    @Column(nullable = false, unique = true)
    private String name;

    @NotNull(message = "No category selected!")
    @ManyToOne
    @JoinColumn(name = "product_category_id")
    private ProductCategory productCategory;

//    // Can be null when adding the product.
    @Builder.Default
    @JsonIgnore // fix recursion in API call.
    @ManyToMany(mappedBy = "products")
    private Set<Dish> dishes = new HashSet<>();

//    public Product setProductCategory(ProductCategory productCategory) {
//        this.productCategory = productCategory;
//        return this;
//    }
}
