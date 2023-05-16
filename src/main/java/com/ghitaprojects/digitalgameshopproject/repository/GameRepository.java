package com.ghitaprojects.digitalgameshopproject.repository;

import com.ghitaprojects.digitalgameshopproject.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {

    List<Game> findByGenre(String genre);

    List<Game> findByDeveloper(String developer);

    List<Game> findByPublisher(String publisher);

    List<Game> findByRating(float rating);

    List<Game> findByPriceLessThan(double price);

    List<Game> findByTitleContainingIgnoreCase(String keyword);

    List<Game> findByGenreAndRatingGreaterThanEqual(String genre, float rating);

}
