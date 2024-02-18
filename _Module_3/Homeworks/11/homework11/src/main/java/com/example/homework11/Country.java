package com.example.homework11;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Setter
@Getter
@NoArgsConstructor
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(min=2, max=20)
    @Column(nullable = false, unique = true)
    @NotBlank(message = "Field cannot be empty")
    private String name;

    @Min(0)
    @Max(1999)
    @Column(nullable = false)
    private double populationInMillions;

    public Country(String name, double populationInMillions) {
        this.name = name;
        this.populationInMillions = populationInMillions;
    }
}
