package com.ghitaprojects.digitalgameshopproject.service;

import com.ghitaprojects.digitalgameshopproject.entity.Game;
import com.ghitaprojects.digitalgameshopproject.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class GameService {

        private final GameRepository gameRepository;

        @Autowired
        public GameService(GameRepository gameRepository) {

            this.gameRepository = gameRepository;
        }

        public Game findById(int id) {

            return gameRepository.findById(id).orElse(null);
        }

        public List<Game> findAll() {

            return gameRepository.findAll();
        }

        @Secured("ROLE_ADMIN")
        public Game save(Game game) {

            return gameRepository.save(game);
        }

        @Secured("ROLE_ADMIN")
        public void delete(Game game) {

            gameRepository.delete(game);
        }


    }
