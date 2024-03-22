package com.h19.h19.Game;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/games")
public class GameRestController {
    @Autowired
    private ModelMapper modelMapper;

    private final GameService gameService;
    private final GameRepository gameRepository;

    public GameRestController(GameService gameService,
                              GameRepository gameRepository) {
        super();
        this.gameService = gameService;
        this.gameRepository = gameRepository;
    }

    //    @GetMapping()
//    public List<GameDTO> getAllGames() {
//        return gameService.getAllGames()
//                .stream()
//                .map(game -> modelMapper.map(game, GameDTO.class))
//                .collect(Collectors.toList());
//    }
    @Transactional
    @GetMapping()
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getGameById(@PathVariable(name = "id") Long id) {
        try {
            Game game = gameService.getGameByID(id);
            GameDTO gameDTO = modelMapper.map(game, GameDTO.class);
            return ResponseEntity.ok().body(gameDTO);
        } catch (RuntimeException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Game with ID " + id + " not found");
        }
    }

    @PostMapping()
    public ResponseEntity<GameDTO> addNewGame(@RequestBody GameDTO gameDTO) {
        Game gameRequest = modelMapper.map(gameDTO, Game.class);
        Game game = gameService.addGame(gameRequest);
        GameDTO response = modelMapper.map(game, GameDTO.class);
        return new ResponseEntity<GameDTO>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GameDTO> updateGame(@PathVariable long id, @RequestBody GameDTO gameDTO) {
        Game gameRequest = modelMapper.map(gameDTO, Game.class);
        Game game = gameService.updateGame(id, gameRequest);
        GameDTO gameDtoResponse = modelMapper.map(game, GameDTO.class);
        return ResponseEntity.ok().body(gameDtoResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGame(@PathVariable long id) {
        try {
            gameService.deleteGame(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Game with ID " + id + " deleted successfully!");
        } catch (RuntimeException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Game with ID " + id + " not found");
        }
    }

}
