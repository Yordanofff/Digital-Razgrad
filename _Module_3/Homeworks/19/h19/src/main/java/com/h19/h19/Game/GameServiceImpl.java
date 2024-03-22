package com.h19.h19.Game;

import com.h19.h19.Company.Company;
import com.h19.h19.Company.CompanyRepository;
import com.h19.h19.Genre.Genre;
import com.h19.h19.Genre.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final CompanyRepository companyRepository;
    private final GenreRepository genreRepository;


    @Override
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @Override
    public Game addGame(Game game) {

        checkIfGameNameAlreadyInDB(game);

        Company company = getOrCreateCompanyIfNotExist(game.getCompany());
        Genre genre = getOrCreateGenreIfNotExist(game.getGenre());

        game.setCompany(company);
        game.setGenre(genre);
        game.setPrice(game.getPrice());

        return gameRepository.save(game);
    }

    @Override
    public Game updateGame(long id, Game gameRequest) {
        Game game = getGameIfGameIdExistsInDB(id);  // Will throw an error if not exist

        // If new Name is not being passed as a parameter - keep the old one.
        if (gameRequest.getName() != null) {
            // Check if the new name is the same as the ID that we are updating
            Long gameIdOfNewName = getGameIdIfGameNameExistsInDB(gameRequest.getName());
            if (!Objects.equals(game.getId(), gameIdOfNewName)) {
                // if not the same - check if the name is already in DB + throw an error.
                checkIfGameNameAlreadyInDB(gameRequest);
            }

            game.setName(gameRequest.getName());
        }

        if (gameRequest.getCompany() != null) {
            Company company = getOrCreateCompanyIfNotExist(gameRequest.getCompany());
            game.setCompany(company);
        }

        if (gameRequest.getGenre() != null) {
            Genre genre = getOrCreateGenreIfNotExist(gameRequest.getGenre());
            game.setGenre(genre);
        }

        if (gameRequest.getPrice() != null) {
            game.setPrice(gameRequest.getPrice());
        }

        if (gameRequest.getYearReleased() != null) {
            game.setYearReleased(gameRequest.getYearReleased());
        }

        return gameRepository.save(game);
    }

    @Override
    public void deleteGame(long id) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Game with ID " + id + " not found"));
        gameRepository.delete(game);
    }

    @Override
    public Game getGameByID(long id) {
        Optional<Game> optionalGame = gameRepository.findById(id);
        if (optionalGame.isPresent()) {
            return optionalGame.get();
        }
        throw new RuntimeException("Game with ID " + id + " not found");
    }

//    private GameDTO convertGameToGameDto(Game game) {
//        GameDTO gameDTO = new GameDTO();
//        gameDTO.setId(game.getId());
//        gameDTO.setName(game.getName());
//        gameDTO.setCompany(game.getCompany());
//        gameDTO.setGenres(game.getGenres());
//        gameDTO.setYearReleased(game.getYearReleased());
//        return gameDTO;
//    }

    private void checkIfGameNameAlreadyInDB(Game game) throws RuntimeException {
        Optional<Game> optionalGame = gameRepository.findByName(game.getName());
        if (optionalGame.isPresent()) {
            throw new RuntimeException("Game: " + game.getName() + " already exists in the DB." + optionalGame.get());
        }
    }

    private Company getOrCreateCompanyIfNotExist(Company company) {
        if (company != null && company.getId() == null) {
            Optional<Company> optionalCompany = companyRepository.findByName(company.getName());
            if (optionalCompany.isEmpty()) {
                company = companyRepository.save(company);
            } else {
                company = optionalCompany.get();
            }
        }
        return company;
    }

    private Genre getOrCreateGenreIfNotExist(Genre genre) {
        if (genre == null) {
            return null;
        }

        // need to check if genre ID + genre name are added - if they match in the DB.
        // If genre Id is in the body, check if there is a genre with that id.
        if (genre.getId() != null){
            Optional<Genre> optionalGenre = genreRepository.findById(genre.getId());
            if (optionalGenre.isPresent()){
                // Also check if the name has been added and if it matches the genre id.
                if (genre.getName() != null) {
                    Optional<Genre> optionalGenre1 = genreRepository.findByName(genre.getName());
                    // If the genre name is not found in the DB but ID is OR
                    // the ID of the found name doesn't match the ID of the genre - throw an error.
                    if (optionalGenre1.isEmpty() || !Objects.equals(optionalGenre1.get().getId(), genre.getId())){
                        throw new RuntimeException("Genre with ID " + genre.getId() + " does not match name " + genre.getName()+ "! \n" + optionalGenre.get());
                    }
                }
                return optionalGenre.get();
            } else {
                throw new RuntimeException("Genre with ID " + genre.getId() + " not found!");
            }
        }

        return getOrCreateGenreIfNameNotExist(genre.getName());
    }

    private Genre getOrCreateGenreIfNameNotExist(String genreName){
        if (genreName == null) {
            return null;
        }

        Genre genre;

        Optional<Genre> optionalGenre = genreRepository.findByName(genreName);
        if (optionalGenre.isEmpty()) {
            genre = genreRepository.save(new Genre(genreName));
        } else {
            genre = optionalGenre.get();
        }

        return genre;
    }


    private Game getGameIfGameIdExistsInDB(Long id) throws RuntimeException {
        Optional<Game> optionalGame = gameRepository.findById(id);
        if (optionalGame.isPresent()) {
            return optionalGame.get();
        }
        throw new RuntimeException("Game with ID " + id + " not found");
    }

//    private Game getGameIfGameNameExistsInDB(String gameName) {
//        Optional<Game> optionalGame = gameRepository.findByName(gameName);
//        if (optionalGame.isPresent()) {
//            return optionalGame.get();
//        }
//        return null;
//    }

    private Long getGameIdIfGameNameExistsInDB(String gameName) {
        Optional<Game> optionalGame = gameRepository.findByName(gameName);
        if (optionalGame.isPresent()) {
            return optionalGame.get().getId();
        }
        return null;
    }
}
