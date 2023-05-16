package com.ghitaprojects.digitalgameshopproject.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "game_rating")
public class GameRating {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "rating_id")
        private int ratingId;

        @ManyToOne
        @JoinColumn(name = "game_id")
        private Game gameId;

        @Column(name = "user_id")
        private int userId;

        @Column(name = "rating")
        private int rating;

}
