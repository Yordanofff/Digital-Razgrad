package com.example.homework11;

import com.example.homework11.Repositories.*;
import com.example.homework11.Entities.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializator implements ApplicationRunner {

    private final GenderRepository genderRepository;
    private final CountryRepository countryRepository;
    private final MovieRepository movieRepository;
    private final ActorRepository actorRepository;
    private final GenreRepository genreRepository;

    public DataInitializator(GenderRepository genderRepository,
                             CountryRepository countryRepository,
                             MovieRepository movieRepository,
                             ActorRepository actorRepository,
                             GenreRepository genreRepository) {
        this.genderRepository = genderRepository;
        this.countryRepository = countryRepository;
        this.movieRepository = movieRepository;
        this.actorRepository = actorRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (genderRepository.count() == 0) {
            genderRepository.save(new Gender("Male"));
            genderRepository.save(new Gender("Female"));
            genderRepository.save(new Gender("Other"));
        }

        if (countryRepository.count() == 0) {
            countryRepository.save(new Country("Bulgaria", 6.7));
            countryRepository.save(new Country("Romania", 52));
            countryRepository.save(new Country("Greece", 10));
            countryRepository.save(new Country("Italy", 100));
        }

        if (genreRepository.count() == 0) {
            genreRepository.save(new Genre("Drama"));
            genreRepository.save(new Genre("Horror"));
            genreRepository.save(new Genre("Comedy"));
            genreRepository.save(new Genre("Action"));
            genreRepository.save(new Genre("Romance"));
        }

        Country bg = countryRepository.findByName("Bulgaria").orElse(null);
        Country it = countryRepository.findByName("Italy").orElse(null);
        Gender male = genderRepository.findByName("Male").orElse(null);
        Gender female = genderRepository.findByName("Female").orElse(null);

        if (actorRepository.count() == 0) {
            actorRepository.save(new Actor("Ivan", "Ivanov", 25, bg, male));
            actorRepository.save(new Actor("Peter", "Petrov", 22, bg, male));
            actorRepository.save(new Actor("Ivanka", "Ivanova", 35, bg, female));
            actorRepository.save(new Actor("Francesco", "Alexandre", 25, it, male));
        }

        Genre drama = genreRepository.findByName("Drama").orElse(null);
        Genre action = genreRepository.findByName("Action").orElse(null);
        Genre romance = genreRepository.findByName("Romance").orElse(null);
        List<Genre> titanicGenres = new ArrayList<>();
        titanicGenres.add(drama);
        titanicGenres.add(romance);

        List<Genre> dieHardGenres = new ArrayList<>();
        dieHardGenres.add(action);

        List<Actor> titanicActors = new ArrayList<>();
        Actor a1 = actorRepository.findById(1L).orElse(null);
        Actor a2 = actorRepository.findById(2L).orElse(null);
        Actor a3 = actorRepository.findById(3L).orElse(null);
        Actor a4 = actorRepository.findById(4L).orElse(null);
        titanicActors.add(a1);
        titanicActors.add(a2);
        titanicActors.add(a3);
        List<Actor> dieHardActors = new ArrayList<>();
        dieHardActors.add(a1);
        dieHardActors.add(a3);
        dieHardActors.add(a4);
        if (movieRepository.count() == 0) {
            movieRepository.save(new Movie("Titanik", titanicGenres, 1995, titanicActors));
            movieRepository.save(new Movie("Die hard", dieHardGenres, 1992, dieHardActors));
        }


    }
}
