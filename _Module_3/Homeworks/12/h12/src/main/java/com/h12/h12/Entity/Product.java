//package com.h12.h12.Entity;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Entity
//@Table
//@Getter
//@Setter
//@NoArgsConstructor
//public class Product {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
//    private Long id;
//
//    @NotBlank(message = "Product name cannot be empty.")
//    @Column(nullable = false, unique = true)
//    private String name;
//
//    @NotNull(message = "No category selected!")
//    @ManyToOne
//    @JoinColumn(name = "product_category_id")
//    private ProductCategory productCategory;
//
//    @ManyToMany
//    private Set<Dish> dishes = new HashSet<>();
//}
