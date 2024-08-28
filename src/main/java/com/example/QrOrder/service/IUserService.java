package com.example.QrOrder.service;

import com.example.QrOrder.models.User;
import com.example.QrOrder.reponses.UserResponse;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    User saveUser(User user);

    Optional<User> getUserById(Integer id);

    User getUserByUsername(String username);

    User getUserByEmail(String email);

     List<User> getAllUsers();

    void deleteUserById(Integer id);
}
