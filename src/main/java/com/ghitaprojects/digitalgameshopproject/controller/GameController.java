package com.ghitaprojects.digitalgameshopproject.controller;

import com.ghitaprojects.digitalgameshopproject.entity.Game;
import com.ghitaprojects.digitalgameshopproject.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public ResponseEntity<List<Game>> getAllGames() {
        try {
            List<Game> games = gameService.findAll();
            return ResponseEntity.ok(games);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable int id) {
        try {
            Game game = gameService.findById(id);
            if (game == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(game);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Secured("ROLE_ADMIN")
    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody Game game) {
        try {
            Game createdGame = gameService.save(game);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdGame);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable int id, @RequestBody Game game) {
        try {
            Game existingGame = gameService.findById(id);
            if (existingGame == null) {
                return ResponseEntity.notFound().build();
            }
            game.setGameId(id);
            Game updatedGame = gameService.save(game);
            return ResponseEntity.ok(updatedGame);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable int id) {
        try {
            Game game = gameService.findById(id);
            if (game == null) {
                return ResponseEntity.notFound().build();
            }
            gameService.delete(game);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}