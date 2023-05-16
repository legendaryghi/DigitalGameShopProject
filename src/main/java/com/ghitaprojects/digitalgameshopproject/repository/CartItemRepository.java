package com.ghitaprojects.digitalgameshopproject.repository;

import com.ghitaprojects.digitalgameshopproject.entity.Cart;
import com.ghitaprojects.digitalgameshopproject.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

    List<CartItem> findAllByCart(Cart cart);

}
