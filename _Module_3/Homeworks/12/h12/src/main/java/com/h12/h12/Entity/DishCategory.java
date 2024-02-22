package com.h12.h12.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Table
@Entity
@Getter
@Setter
public class DishCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

//    @NotBlank
//    @Size(min = 3, max = 30, message = "The category cannot be less than 3 and more than 30 symbols long.")
//    @Column(nullable = false, unique = true)
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;
}
