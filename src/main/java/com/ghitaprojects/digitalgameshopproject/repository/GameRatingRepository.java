package com.ghitaprojects.digitalgameshopproject.repository;

import com.ghitaprojects.digitalgameshopproject.entity.GameRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRatingRepository extends JpaRepository<GameRating, Integer> {


    List<GameRating> findAllByGameId(int gameId);

    List<GameRating> findAllByUserId(int userId);

    List<GameRating> findAllByRatingGreaterThanEqual(float rating);

}
