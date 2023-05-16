package com.ghitaprojects.digitalgameshopproject.entity;


import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private int cartId;

    @Column(name = "user_id")
    private int userId;


    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "cart", cascade = CascadeType.ALL)
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartItem> cartItems;

    public User getUser() {
        return user;
    }

    public double calculateTotalPrice() {
        double totalPrice = 0.0;
        for (CartItem cartItem : cartItems) {
            totalPrice += cartItem.getGame().getPrice() * cartItem.getQuantity();
        }
        return totalPrice;
    }

}