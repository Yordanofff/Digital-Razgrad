package com.example.homework11;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ActorController {

    private final CountryRepository countryRepository;
    private final GenderRepository genderRepository;
    private final ActorRepository actorRepository;

    public ActorController(CountryRepository countryRepository,
                           GenderRepository genderRepository,
                           ActorRepository actorRepository) {
        this.countryRepository = countryRepository;
        this.genderRepository = genderRepository;
        this.actorRepository = actorRepository;
    }

    @GetMapping("/actors")
    public String getActors(Model model) {
        model.addAttribute("countries", countryRepository.findAll());
        model.addAttribute("genders", genderRepository.findAll());
        model.addAttribute("actors", actorRepository.findAll());
        model.addAttribute("actor", new Actor());
        return "actors";
    }

    @PostMapping("/actors/add")
    public String addActor(@ModelAttribute Actor actor){
        actorRepository.save(actor);
        return "redirect:/actors";
    }
}
