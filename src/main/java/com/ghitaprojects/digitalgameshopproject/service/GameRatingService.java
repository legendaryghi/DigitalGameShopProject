package com.ghitaprojects.digitalgameshopproject.service;

import com.ghitaprojects.digitalgameshopproject.entity.GameRating;
import com.ghitaprojects.digitalgameshopproject.repository.GameRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GameRatingService {

    private final GameRatingRepository gameRatingRepository;

    @Autowired
    public GameRatingService(GameRatingRepository gameRatingRepository) {
        this.gameRatingRepository = gameRatingRepository;
    }

    public GameRating findById(int gameId) {

        return gameRatingRepository.findById(gameId).orElse(null);
    }


    public List<GameRating> findAllByGameId(int gameId) {

        return gameRatingRepository.findAllByGameId(gameId);
    }

    public List<GameRating> findAllByUserId(int userId) {

        return gameRatingRepository.findAllByUserId(userId);
    }

    public List<GameRating> findAllByRatingGreaterThanEqual(float rating) {
        return gameRatingRepository.findAllByRatingGreaterThanEqual(rating);
    }

    public GameRating save(GameRating gameRating) {

        return gameRatingRepository.save(gameRating);
    }

    public void delete(GameRating gameRating) {

        gameRatingRepository.delete(gameRating);
    }

}