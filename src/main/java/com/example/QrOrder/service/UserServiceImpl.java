package com.example.QrOrder.service;

import com.example.QrOrder.models.User;

import com.example.QrOrder.reponses.UserResponse;
import com.example.QrOrder.repository.UserRepository;
import com.example.QrOrder.service.IUserService;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public User getUserByUsername(String username) {
        //   return userRepository.findByUsername(username);
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        Optional<User> userDetailsOp = userRepository.findByEmail(email);
        if (userDetailsOp.isEmpty()) {

        }
        User user = userDetailsOp.get();
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return  userRepository.findAll();
    }

    @Override
    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
    }
}
