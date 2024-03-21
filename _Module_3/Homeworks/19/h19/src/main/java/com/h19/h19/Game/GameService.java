package com.h19.h19.Game;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GameService {
    List<Game> getAllGames();

    Game addGame(Game game);
    Game updateGame(long id, Game game);

    void deleteGame(long id);

    Game getGameByID(long id);

}
