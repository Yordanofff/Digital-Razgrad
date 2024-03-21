package com.h19.h19.Game;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;


    @Override
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @Override
    public Game addGame(Game game) {
        return gameRepository.save(game);
    }

    @Override
    public Game updateGame(long id, Game gameRequest) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Game with ID " + id + " not found"));

        game.setName(gameRequest.getName());
        game.setCompany(gameRequest.getCompany());
        game.setPrice(gameRequest.getPrice());
        game.setYearReleased(gameRequest.getYearReleased());
        game.setGenres(gameRequest.getGenres());
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
}
