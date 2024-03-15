package com.example.homework11.service;

import Utils.DateTimeUtils;
import com.example.homework11.DTO.ActorDTO;
import com.example.homework11.Entities.Actor;
import com.example.homework11.Repositories.ActorRepository;
import com.example.homework11.Repositories.CountryRepository;
import com.example.homework11.Repositories.GenderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActorService {

    private final CountryRepository countryRepository;
    private final GenderRepository genderRepository;
    private final ActorRepository actorRepository;

    public String getActors(Model model) {
        model.addAttribute("countries", countryRepository.findAll());
        model.addAttribute("genders", genderRepository.findAll());
        model.addAttribute("actors", actorRepository.findAll()
                .stream()
                .map(this::convertActorToDTO)
                .collect(Collectors.toList()));
        model.addAttribute("actor", new Actor());
        return "actors";
    }

    public String addActor(ActorDTO actorDTO) {
        actorRepository.save(convertActorDTOToActor(actorDTO));
        return "redirect:/actors";
    }

    private ActorDTO convertActorToDTO(Actor actor) {
        return new ActorDTO(
                actor.getFirstName(),
                actor.getFamilyName(),
                actor.getAge(),
                actor.getCountry(),
                actor.getGender(),
                actor.getMovieList(),
                DateTimeUtils.isBornInLeapYear(actor.getAge()),
                DateTimeUtils.calculateYearBorn(actor.getAge())
        );
    }

    private Actor convertActorDTOToActor(ActorDTO actorDTO) {
        return new Actor(
                actorDTO.getFirstName(),
                actorDTO.getFamilyName(),
                actorDTO.getAge(),
                actorDTO.getCountry(),
                actorDTO.getGender()
        );
    }
}
