package com.example.coffee.service;

import com.example.coffee.model.User;
import com.example.coffee.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
        public UserService(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        public List<User> getAllUser() {
            return userRepository.findAll();
        }

        public User findCustomerById(Long id) {
            return userRepository.findById(id)
                    .stream()
                    .filter(customer -> customer.getId().equals(id))
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("customer ID not Found"));
            }
        public User updateUser(User userToUpdate) {
            Optional<User> existingUser = userRepository.findById(userToUpdate.getId());
            if (existingUser.isPresent()) {
                userToUpdate.setName(userToUpdate.getName());
                userToUpdate.setEmail(userToUpdate.getEmail());
                userToUpdate.setPassword(userToUpdate.getPassword());
                userRepository.save(userToUpdate);
                return userToUpdate;
            }
            return null;
        }

        public User addUser(User user) {
            return userRepository.save(user);
        }

        @Transactional
        public User deleteUser(Long id) {
            userRepository.deleteById(id);
            return null;
        }


}
