package com.ghitaprojects.digitalgameshopproject.service;

import com.ghitaprojects.digitalgameshopproject.entity.Order;
import com.ghitaprojects.digitalgameshopproject.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order findById(int id) {
        return orderRepository.findById(id).orElse(null);
    }

    public List<Order> findAllByUserIdAndUserIdNot(int userId, int currentUserId) {
        return orderRepository.findAllByUserIdAndUserIdNot(userId, currentUserId);
    }

    public List<Order> findAllByCartId_CartId(int cartId) {
        return orderRepository.findAllByCart_CartId(cartId);
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public void delete(Order order) {
        orderRepository.delete(order);
    }


}