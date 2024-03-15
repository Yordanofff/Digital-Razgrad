package com.example.homework11.Controllers;

import com.example.homework11.DTO.ActorDTO;
import com.example.homework11.Entities.Actor;
import com.example.homework11.Repositories.ActorRepository;
import com.example.homework11.Repositories.CountryRepository;
import com.example.homework11.Repositories.GenderRepository;
import com.example.homework11.service.ActorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ActorController {
    private final ActorService actorService;


    @GetMapping("/actors")
    public String getActors(Model model) {
        return actorService.getActors(model);
    }

    @PostMapping("/actors/add")
    public String addActor(@ModelAttribute ActorDTO actorDTO){
        return actorService.addActor(actorDTO);
    }
}
