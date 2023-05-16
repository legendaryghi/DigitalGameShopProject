package com.ghitaprojects.digitalgameshopproject.controller;

import com.ghitaprojects.digitalgameshopproject.entity.Cart;
import com.ghitaprojects.digitalgameshopproject.entity.Order;
import com.ghitaprojects.digitalgameshopproject.service.CartService;
import com.ghitaprojects.digitalgameshopproject.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final CartService cartService;

    @Autowired
    public OrderController(OrderService orderService, CartService cartService) {
        this.orderService = orderService;
        this.cartService = cartService;
    }


    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@AuthenticationPrincipal Long userId) {
        Cart cart = cartService.findByUserId(userId.intValue());
        if (cart == null || cart.getCartItems().isEmpty()) {
            return ResponseEntity.badRequest().body("No items in the cart.");
        }

        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setTotalPrice(cart.calculateTotalPrice());
        order.setOrderAddress(""); // Set the order address as per your requirement
        order.setCart(cart);
        order.setUserId(userId);

        Order savedOrder = orderService.save(order);
        cartService.delete(cart);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable int orderId) {
        Order order = orderService.findById(orderId);
        if (order == null) {
            return ResponseEntity.notFound().header("message", "Order not found.").build();
        }
        return ResponseEntity.ok(order);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getOrdersByUserId(@PathVariable Long userId) {
        List<Order> orders = orderService.findAllByUserIdAndUserIdNot(userId.intValue(), userId.intValue());
        return ResponseEntity.ok(orders);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable int orderId) {
        Order order = orderService.findById(orderId);
        if (order == null) {
            return ResponseEntity.notFound().header("message", "Order not found.").build();
        }

        orderService.delete(order);
        return ResponseEntity.noContent().build();
    }
}