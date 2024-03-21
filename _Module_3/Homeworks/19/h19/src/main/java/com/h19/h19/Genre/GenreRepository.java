package com.h19.h19.Genre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    Optional<Genre> findByName(String name);

//    @Query("SELECT new com.h19.h19.Genre.GenreDTO(g.id, g.name) FROM Genres g")
//    List<GenreDTO> findAllGenres();
}
