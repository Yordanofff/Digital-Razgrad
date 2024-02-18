package com.example.homework11;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(min = 2, max = 20)
    @Column(nullable = false)
    private String firstName;

    @Size(min = 2, max = 20)
    @Column(nullable = false)
    private String familyName;

    @Min(1)
    @Max(120)
    @Column(nullable = false)
    private int age;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @ManyToOne
    @JoinColumn(name = "gender_id", nullable = false)
    private Gender gender;

    // Can be null when actor is added.
    @ManyToMany(mappedBy = "actorList")
    List<Movie> movieList = new ArrayList<>();

    public Actor(String firstName, String familyName, int age, Country country, Gender gender) {
        this.firstName = firstName;
        this.familyName = familyName;
        this.age = age;
        this.country = country;
        this.gender = gender;
    }
}
