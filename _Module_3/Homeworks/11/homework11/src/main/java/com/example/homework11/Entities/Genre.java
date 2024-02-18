package com.example.homework11.Entities;

import jakarta.persistence.*;
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
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(min = 2, max = 50)
    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "genreList")
    List<Movie> movieList = new ArrayList<>();

    public Genre(String name) {
        this.name = name;
    }
}
