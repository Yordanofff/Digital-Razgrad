package com.h12.h12.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Table
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "Dish name cannot be empty!")
    @Size(min = 3, max = 30, message = "Size must be between 3 and 30 symbols")
    @Column(nullable = false) // No need to be unique.
    private String name;

    // Can be null
    private String description;

    @NotNull(message = "No category selected!")
    @ManyToOne
    @JoinColumn(name = "dish_category_id")
    private DishCategory dishCategory;

    @NotEmpty(message = "Enter at least one product from the list below!")
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "product_dish",
            joinColumns = @JoinColumn(name = "dish_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> products = new HashSet<>();

    @Transient
    private int rating;

//    public Dish setDishCategory(DishCategory dishCategory) {
//        this.dishCategory = dishCategory;
//        return this;
//    }
}
