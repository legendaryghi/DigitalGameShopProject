package com.ghitaprojects.digitalgameshopproject.service;

import com.ghitaprojects.digitalgameshopproject.entity.Cart;
import com.ghitaprojects.digitalgameshopproject.entity.CartItem;
import com.ghitaprojects.digitalgameshopproject.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CartItemService {

    private final CartItemRepository cartItemRepository;

    @Autowired
    public CartItemService(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    public CartItem findById(int id) {
        return cartItemRepository.findById(id).orElse(null);
    }

    public List<CartItem> findAllByCart(Cart cart) {

        return cartItemRepository.findAllByCart(cart);
    }

    public CartItem save(CartItem cartItem) {

        return cartItemRepository.save(cartItem);
    }

    public void delete(CartItem cartItem) {

        cartItemRepository.delete(cartItem);
    }

}
