package com.ghitaprojects.digitalgameshopproject.service;

import com.ghitaprojects.digitalgameshopproject.entity.Cart;
import com.ghitaprojects.digitalgameshopproject.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CartService {

    private final CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository) {

        this.cartRepository = cartRepository;
    }

    public Cart findByUserId(int userId) {

        return cartRepository.findByUserId(userId).orElse(null);
    }

    public Cart findById(int cartId) {
        return cartRepository.findById(cartId).orElse(null);
    }
    public Cart save(Cart cart) {

        return cartRepository.save(cart);
    }

    public void delete(Cart cart) {

        cartRepository.delete(cart);
    }


}