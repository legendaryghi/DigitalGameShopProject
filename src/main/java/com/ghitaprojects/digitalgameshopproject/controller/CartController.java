package com.ghitaprojects.digitalgameshopproject.controller;

import com.ghitaprojects.digitalgameshopproject.entity.Cart;
import com.ghitaprojects.digitalgameshopproject.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<Map<String, Object>> getCart(@PathVariable int cartId) {
        try {
            Cart cart = cartService.findById(cartId);
            if (cart == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(createErrorResponse("Cart not found"));
            } else {
                return ResponseEntity.ok(createSuccessResponse(cart));
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(createErrorResponse("Failed to retrieve the cart"));
        }
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Map<String, Object>> createCart(@PathVariable int userId) {
        try {
            // Check if the user already has a cart
            Cart existingCart = cartService.findByUserId(userId);
            if (existingCart != null) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(createErrorResponse("A cart already exists for this user"));
            } else {
                // Create a new cart
                Cart newCart = new Cart();
                newCart.setUserId(userId);
                Cart savedCart = cartService.save(newCart);
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(createSuccessResponse(savedCart));
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(createErrorResponse("Failed to create the cart"));
        }
    }

    @DeleteMapping("/{cartId}")
    public ResponseEntity<Map<String, Object>> deleteCart(@PathVariable int cartId) {
        try {
            Cart cart = cartService.findById(cartId);
            if (cart == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(createErrorResponse("Cart not found"));
            } else {
                cartService.delete(cart);
                return ResponseEntity.noContent().build();
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(createErrorResponse("Failed to delete the cart"));
        }
    }

    private Map<String, Object> createSuccessResponse(Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put("data", data);
        return response;
    }

    private Map<String, Object> createErrorResponse(String errorMessage) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", errorMessage);
        return response;
    }
}