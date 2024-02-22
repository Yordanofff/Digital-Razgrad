//package com.h12.h12.Entity;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;
//import lombok.Getter;
//import lombok.Setter;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Table
//@Entity
//@Getter
//@Setter
//public class Dish {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
//    private Long id;
//
//    @NotBlank
//    @Column(nullable = false) // No need to be unique.
//    private String name;
//
//    @NotNull
//    @ManyToOne
//    @JoinColumn(name = "dish_category_id")
//    private DishCategory dishCategory;
//
//    @ManyToMany(mappedBy = "dishes")
//    Set<Product> products = new HashSet<>();
//}
