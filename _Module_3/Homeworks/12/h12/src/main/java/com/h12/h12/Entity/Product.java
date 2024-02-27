package com.h12.h12.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
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
    @ManyToMany(mappedBy = "products")
    private Set<Dish> dishes = new HashSet<>();

}
