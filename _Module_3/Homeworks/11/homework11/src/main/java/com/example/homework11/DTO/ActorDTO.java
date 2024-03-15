package com.example.homework11.DTO;

import com.example.homework11.Entities.Country;
import com.example.homework11.Entities.Gender;
import com.example.homework11.Entities.Movie;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ActorDTO {
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

//    @ManyToOne
    @Column(nullable = false)
    private Country country;

//    @ManyToOne
    @Column(nullable = false)
    private Gender gender;

    // Can be null when actor is added.
    List<Movie> movieList = new ArrayList<>();

    public ActorDTO(String firstName, String familyName, int age, Country country, Gender gender, List<Movie> movieList) {
        this.firstName = firstName;
        this.familyName = familyName;
        this.age = age;
        this.country = country;
        this.gender = gender;
        this.movieList = movieList;
    }
}
