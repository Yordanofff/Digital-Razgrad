package com.h19.h19.Game;

import com.h19.h19.Company.Company;
import com.h19.h19.Genre.Genre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="games", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private int yearReleased;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "games_genres",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    private double price;
}
