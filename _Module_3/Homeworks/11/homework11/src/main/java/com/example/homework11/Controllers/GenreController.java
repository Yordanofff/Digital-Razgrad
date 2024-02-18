package com.example.homework11.Controllers;

import com.example.homework11.Entities.Genre;
import com.example.homework11.Repositories.GenreRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.context.support.DefaultMessageSourceResolvable;

import java.util.List;

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

//    @PostMapping("/add")
//    public String addGenre(@ModelAttribute Genre genre) {
//        genreRepository.save(genre);
//        return "redirect:/movies";
//    }

    @PostMapping("/add")
    public ResponseEntity<String> addGenre(@Valid @ModelAttribute Genre genre, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

            List<String> errorMessages = bindingResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();

            return ResponseEntity.badRequest().body(errorMessages.toString());
        } else {
            genreRepository.save(genre);
            return ResponseEntity.ok("Genre added successfully");
        }
    }
}
