package com.example.homework11.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(min=1, max=30)
    @Column(nullable = false)  // no need to be unique
    private String name;

    @ManyToMany
    @Column(nullable = false)
    @JoinTable(
            name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genreList;

    @Min(1900)
    @Max(2100)
    @Column(nullable = false)
    private int year;

    @ManyToMany
    @Column(nullable = false)
    @JoinTable(
            name = "movie_actor",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    private List<Actor> actorList;

    public Movie(String name, List<Genre> genreList, int year, List<Actor> actorList) {
        this.name = name;
        this.genreList = genreList;
        this.year = year;
        this.actorList = actorList;
    }
}
