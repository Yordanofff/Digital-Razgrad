package com.example.Task8;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/genres")
public class GenreController {
    private GenreRepository genreRepository;

    public GenreController(GenreRepository genreRepository){
        this.genreRepository = genreRepository;
    }

    @GetMapping("/get")
    public Iterable<Genre> getGenres(){
        return genreRepository.findAll();
    }

    @PostMapping("/add")
    public Genre addGenre(@RequestParam String name){
        Genre genre = new Genre();
        genre.setGenre(name);
        genreRepository.save(genre);
        return genre;
    }

}
