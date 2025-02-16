package com.example.coffee.controller;

import com.example.coffee.model.User;
import com.example.coffee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<User> getAllUser() {
        return userService.getAllUser();
    }
    @GetMapping(path = "{userId}")
    public User findCustomerById(@PathVariable("userId") Long id) {
        return userService.findCustomerById(id);
    }
    @PostMapping("/add")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PutMapping("/update")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/delete")
    public User deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}
