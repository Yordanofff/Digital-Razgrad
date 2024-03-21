package com.h19.h19;

import com.h19.h19.Company.Company;
import com.h19.h19.Company.CompanyRepository;
import com.h19.h19.Game.Game;
import com.h19.h19.Game.GameRepository;
import com.h19.h19.Genre.Genre;
import com.h19.h19.Genre.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInit implements ApplicationRunner {
    private final GenreRepository genreRepository;
    private final CompanyRepository companyRepository;
    private final GameRepository gameRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (genreRepository.count() == 0) {
            List<String> genres = List.of("Action", "RPG", "Fighting", "Strategy", "Shooter", "Racing", "Survival", "Sports");
            for (String genre : genres) {
                genreRepository.save(new Genre(genre));
            }
        }

        if (companyRepository.count() == 0) {
            List<String> companies = List.of("Valve", "Microsoft", "SEGA", "SONY", "EA", "UBISOFT", "BLIZZARD", "Nintendo");
            for (String company : companies) {
                companyRepository.save(new Company(company));
            }
        }

        genreRepository.flush();

        if (gameRepository.count() == 0) {

            Set<Genre> genresSet = new HashSet<>();
            Genre actionGenre = genreRepository.findByName("Action").orElse(null);
            if (actionGenre != null) {
                genresSet.add(actionGenre);
            }

            Genre shooterGenre = genreRepository.findByName("Shooter").orElse(null);
            if (shooterGenre != null) {
                genresSet.add(shooterGenre);
            }

            gameRepository.save(Game.builder()
                    .name("Counter-Strike")
                    .yearReleased(2000)
                    .company(companyRepository.findByName("Valve").orElse(null))
                    .price(20)
                    .genres(genresSet)
                    .build());
        }
    }
}
