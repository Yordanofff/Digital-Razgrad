package com.example.homework11;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/genres")
public class GenreController {

    private GenreRepository genreRepository;

    public GenreController(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @GetMapping
    public String getGenres(Model model) {
        model.addAttribute("genres", genreRepository.findAll());
        model.addAttribute("genre", new Genre());
        return "genres";
    }

    @PostMapping("/add")
    public String addGenre(@ModelAttribute Genre genre) {
        genreRepository.save(genre);
        return "redirect:/movies";
    }
}
