package com.example.homework11.Controllers;

import com.example.homework11.DTO.MovieDTO;
import com.example.homework11.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public String getMovies(Model model) {
        return movieService.getMovies(model);
    }

    @PostMapping("/add")
    public String addMovie(@Valid @ModelAttribute MovieDTO movieDTO,
                           BindingResult bindingResult,
                           @RequestParam(name = "actorIds", required = false) List<Long> actorIds,
                            @RequestParam(name = "genresIds", required = false) List<Long> genresIds,
                           Model model){
        return movieService.addMovie(movieDTO, bindingResult, actorIds, genresIds, model);
    }

}
