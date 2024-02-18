package com.example.homework11.Controllers;

import com.example.homework11.Entities.Actor;
import com.example.homework11.Entities.Genre;
import com.example.homework11.Entities.Movie;
import com.example.homework11.Repositories.ActorRepository;
import com.example.homework11.Repositories.GenreRepository;
import com.example.homework11.Repositories.MovieRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public String addMovie(@ModelAttribute Movie movie,
                           @RequestParam("actorIds") List<Long> actorIds,
                            @RequestParam("genresIds") List<Long> genresIds){
        List<Actor> selectedActors = actorRepository.findAllById(actorIds);
        List<Genre> selectedGenres = genreRepository.findAllById(genresIds);
        movie.setActorList(selectedActors);
        movie.setGenreList(selectedGenres);
        movieRepository.save(movie);
        return "redirect:/movies";
    }

}
