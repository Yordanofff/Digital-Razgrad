package com.h12.h12.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
public class DishCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    @Size(min = 3, max = 20)
    private String name;

    public DishCategory(String name) {
        this.name = name;
    }
}
