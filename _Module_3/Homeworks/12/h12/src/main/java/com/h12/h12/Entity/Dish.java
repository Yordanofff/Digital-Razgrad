package com.h12.h12.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Table
@Entity
@Getter
@Setter
@ToString
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 30)
    @Column(nullable = false) // No need to be unique.
    private String name;

    // Can be null
    private String description;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "dish_category_id")
    private DishCategory dishCategory;

    @NotEmpty
    @ManyToMany(mappedBy = "dishes")
    private Set<Product> products = new HashSet<>();

}
