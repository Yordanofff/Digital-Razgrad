package com.example.homework11;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/movies")
public class MovieController {

    private final MovieRepository movieRepository;
    private final ActorRepository actorRepository;
    private final GenreRepository genreRepository;

    public MovieController(MovieRepository movieRepository,
                           ActorRepository actorRepository,
                           GenreRepository genreRepository) {
        this.movieRepository = movieRepository;
        this.actorRepository = actorRepository;
        this.genreRepository = genreRepository;
    }

    @GetMapping
    public String getMovies(Model model) {
        model.addAttribute("movies", movieRepository.findAll());
        model.addAttribute("actors", actorRepository.findAll());
        model.addAttribute("genres", genreRepository.findAll());
        model.addAttribute("movie", new Movie());
        return "movies";
    }

    @PostMapping("/add")
    public String addMovie(@ModelAttribute Movie movie, @RequestParam("actorIds") List<Long> actorIds){
        List<Actor> selectedActors = actorRepository.findAllById(actorIds);
        movie.setActorList(selectedActors);
        movieRepository.save(movie);
        return "redirect:/movies";
    }

}