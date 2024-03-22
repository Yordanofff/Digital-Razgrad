package com.h19.h19.Game;

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

    public GameRestController(GameService gameService) {
        super();
        this.gameService = gameService;
    }

    @GetMapping()
    public List<GameDTO> getAllGames() {
        return gameService.getAllGames()
                .stream()
                .map(game -> modelMapper.map(game, GameDTO.class))
                .collect(Collectors.toList());
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
    public ResponseEntity<?> addNewGame(@RequestBody Game gameRequest) {
        try {
            Game game = gameService.addGame(gameRequest);
            GameDTO response = modelMapper.map(game, GameDTO.class);
            return new ResponseEntity<GameDTO>(response, HttpStatus.CREATED);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.FOUND).body(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateGame(@PathVariable long id, @RequestBody Game gameRequest) {
        try {
            Game game = gameService.updateGame(id, gameRequest);
            GameDTO gameDtoResponse = modelMapper.map(game, GameDTO.class);
            return ResponseEntity.ok().body(gameDtoResponse);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
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
