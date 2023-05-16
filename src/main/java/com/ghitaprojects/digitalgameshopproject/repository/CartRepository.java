package com.ghitaprojects.digitalgameshopproject.repository;

import com.ghitaprojects.digitalgameshopproject.entity.Cart;
import com.ghitaprojects.digitalgameshopproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    Optional<Cart> findByUserId(int userId);

    List<Cart> findAllByUserId(int userId);

    @Query("SELECT c FROM Cart c JOIN c.cartItems ci JOIN ci.game g WHERE c.user.userId = :userId AND g.gameId = :gameId")
    List<Cart> findAllByUserIdAndGameId(@Param("userId") int userId, @Param("gameId") int gameId);

    boolean existsByUserId(int userId);
    Cart findByUser(User user);
}