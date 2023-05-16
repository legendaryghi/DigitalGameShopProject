package com.ghitaprojects.digitalgameshopproject.controller;

import com.ghitaprojects.digitalgameshopproject.entity.GameRating;
import com.ghitaprojects.digitalgameshopproject.service.GameRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game-ratings")
public class GameRatingController {

    private final GameRatingService gameRatingService;

    @Autowired
    public GameRatingController(GameRatingService gameRatingService) {
        this.gameRatingService = gameRatingService;
    }

    @GetMapping("/{ratingId}")
    public ResponseEntity<GameRating> getGameRating(@PathVariable int ratingId) {
        try {
            GameRating gameRating = gameRatingService.findById(ratingId);
            if (gameRating == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(gameRating);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<GameRating> createGameRating(@RequestBody GameRating gameRating) {
        try {
            GameRating savedGameRating = gameRatingService.save(gameRating);
            return ResponseEntity.ok(savedGameRating);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{ratingId}")
    public ResponseEntity<GameRating> updateGameRating(
            @PathVariable int ratingId, @RequestBody GameRating gameRating) {
        try {
            GameRating existingGameRating = gameRatingService.findById(ratingId);
            if (existingGameRating == null) {
                return ResponseEntity.notFound().build();
            }
            existingGameRating.setRating(gameRating.getRating());
            GameRating updatedGameRating = gameRatingService.save(existingGameRating);
            return ResponseEntity.ok(updatedGameRating);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{ratingId}")
    public ResponseEntity<Void> deleteGameRating(@PathVariable int ratingId) {
        try {
            GameRating gameRating = gameRatingService.findById(ratingId);
            if (gameRating == null) {
                return ResponseEntity.notFound().build();
            }
            gameRatingService.delete(gameRating);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}