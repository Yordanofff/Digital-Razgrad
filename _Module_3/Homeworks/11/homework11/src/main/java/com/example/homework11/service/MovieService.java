package com.example.homework11.service;

import com.example.homework11.DTO.MovieDTO;
import com.example.homework11.Entities.Actor;
import com.example.homework11.Entities.Genre;
import com.example.homework11.Entities.Movie;
import com.example.homework11.Repositories.ActorRepository;
import com.example.homework11.Repositories.GenreRepository;
import com.example.homework11.Repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final ActorRepository actorRepository;
    private final GenreRepository genreRepository;

    public String getMovies(Model model) {
        model.addAttribute("actors", actorRepository.findAll());
        model.addAttribute("genres", genreRepository.findAll());
        model.addAttribute("movies", movieRepository.findAll());
        model.addAttribute("movie", new MovieDTO());
        return "movies";
    }

    public String addMovie(MovieDTO movieDTO, BindingResult bindingResult, List<Long> actorIds, List<Long> genresIds, Model model) {

//        // bindingResult is not returning any validation errors from the DTO/Entity annotations
//        if (bindingResult.hasErrors()){
//            model.addAttribute("actors", actorRepository.findAll());
//            model.addAttribute("genres", genreRepository.findAll());
//            model.addAttribute("movies", movieRepository.findAll());
//            model.addAttribute("movie", movieDTO);
//            return "movies";
//        }


        // Adding a check for the year and name because bindingResult errors are not being displayed..
        if (movieDTO.getName().isEmpty() || movieDTO.getName().length() > 30) {
            model.addAttribute("actors", actorRepository.findAll());
            model.addAttribute("genres", genreRepository.findAll());
            model.addAttribute("movies", movieRepository.findAll());
            model.addAttribute("movie", movieDTO);
            model.addAttribute("nameEmpty", "The movie name should be between 1 and 30 symbols!");
            return "movies";
        }

        if (movieDTO.getConfirmedName().isEmpty() || movieDTO.getConfirmedName().length() > 30) {
            model.addAttribute("actors", actorRepository.findAll());
            model.addAttribute("genres", genreRepository.findAll());
            model.addAttribute("movies", movieRepository.findAll());
            model.addAttribute("movie", movieDTO);
            model.addAttribute("confirmedNameEmpty", "The movie name should be between 1 and 30 symbols!");
            return "movies";
        }

        if (!areMovieNamesSame(movieDTO.getName(), movieDTO.getConfirmedName())) {
            model.addAttribute("actors", actorRepository.findAll());
            model.addAttribute("genres", genreRepository.findAll());
            model.addAttribute("movies", movieRepository.findAll());
            model.addAttribute("movie", movieDTO);
            model.addAttribute("moviesNotMatch", "Movie names don't match!");
            return "movies";
        }

        if (genresIds == null || genresIds.isEmpty()) {
            model.addAttribute("actors", actorRepository.findAll());
            model.addAttribute("genres", genreRepository.findAll());
            model.addAttribute("movies", movieRepository.findAll());
            model.addAttribute("movie", movieDTO);
            model.addAttribute("noGenres", "Please select at least one genre!");
            return "movies";
        }

        if (movieDTO.getYear() < 1900 || movieDTO.getYear() > 2100) {
            model.addAttribute("actors", actorRepository.findAll());
            model.addAttribute("genres", genreRepository.findAll());
            model.addAttribute("movies", movieRepository.findAll());
            model.addAttribute("movie", movieDTO);
            model.addAttribute("yearNoMatch", "Please enter year between 1900 and 2100!");
            return "movies";
        }

        if (actorIds == null || actorIds.isEmpty()) {
            model.addAttribute("actors", actorRepository.findAll());
            model.addAttribute("genres", genreRepository.findAll());
            model.addAttribute("movies", movieRepository.findAll());
            model.addAttribute("movie", movieDTO);
            model.addAttribute("noActors", "Please select at least one actor!");
            return "movies";
        }

        List<Actor> selectedActors = actorRepository.findAllById(actorIds);
        List<Genre> selectedGenres = genreRepository.findAllById(genresIds);
        movieDTO.setActorList(selectedActors);
        movieDTO.setGenreList(selectedGenres);


        movieRepository.save(convertMovieDTOToMovie(movieDTO));
        return "redirect:/movies";
    }


    private boolean areMovieNamesSame(String name, String confirmedName) {
        return name.equals(confirmedName);
    }

    private Movie convertMovieDTOToMovie(MovieDTO movieDTO) {
        // Accessing a property to trigger initialization
        // Fixing issue of Hibernate where it handles lazy loading and proxy initialization for relationships between entities.
        List<Actor> actorList = movieDTO.getActorList();
        actorList.forEach(Actor::getId);

        List<Genre> genreList = movieDTO.getGenreList();
        genreList.forEach(Genre::getId);

        return new Movie(
                movieDTO.getName(),
                genreList,
                movieDTO.getYear(),
                actorList
        );
    }
}
