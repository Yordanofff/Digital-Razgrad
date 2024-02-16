package com.example.homework11;

import jakarta.persistence.*;
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

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String familyName;

    @Column(nullable = false)
    private int age;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @ManyToOne
    @JoinColumn(name = "gender_id", nullable = false)
    private Gender gender;

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
