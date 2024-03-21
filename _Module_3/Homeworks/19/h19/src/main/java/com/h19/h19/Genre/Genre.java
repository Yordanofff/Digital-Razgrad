package com.h19.h19.Genre;

import com.h19.h19.Game.Game;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "genres")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(min=1, max=20)
    @Column(nullable = false, unique = true)
    private String name;

//    @ManyToMany(mappedBy = "genres")
    @ManyToMany(mappedBy = "genres", fetch = FetchType.EAGER)
    private Set<Game> games;

    public Genre(String name) {
        this.name = name;
    }
}
