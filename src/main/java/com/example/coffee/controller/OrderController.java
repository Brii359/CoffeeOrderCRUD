package com.example.coffee.controller;

import com.example.coffee.model.Order;
import com.example.coffee.model.User;
import com.example.coffee.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/order")
public class OrderController {

    @Autowired
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/customer/{customerId}")
    public Order createOrder(@PathVariable Long userId, @RequestBody Order order) {
        return orderService.createOrder(userId, order);
    }

    @GetMapping
    public List<Order> getAllOrderOfCustomer(@RequestBody Order order ){
        return orderService.getAllOrder();
    }

    @PutMapping
    public Order updateUserOrder(@RequestBody Order order, Long customerId) {
        return orderService.updateOrder(order);
    }

    @DeleteMapping
    public Order deleteByOrderId(@PathVariable Long id) {
        return orderService.deleteByOrderId(id);
    }
}
