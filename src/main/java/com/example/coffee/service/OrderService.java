package com.example.coffee.service;

import com.example.coffee.model.Order;
import com.example.coffee.model.User;
import com.example.coffee.repository.OrderRepository;
import com.example.coffee.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private final OrderRepository orderRepository;
    @Autowired
    private final UserRepository userRepository;


    public OrderService(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    public Order createOrder(Long userId, Order order) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Customer Not Found"));
        order.setUser(user);
        return orderRepository.save(order);
    }


    public Order updateOrder(Order updateOrder){
        Optional<Order> existingOrder = orderRepository.findById(updateOrder.getOrderId());
        if (existingOrder.isPresent()) {
            updateOrder.setUser(updateOrder.getUser());
            updateOrder.setCoffee_name(updateOrder.getCoffee_name());
            updateOrder.setBread(updateOrder.getBread());

            orderRepository.save(updateOrder);
            return updateOrder;
        }
        return null;
    }

    @Transactional
    public Order deleteByOrderId(Long id) {
        orderRepository.deleteById(id);
        return null;
    }
}
