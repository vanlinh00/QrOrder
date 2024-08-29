package com.example.QrOrder.service;

import com.example.QrOrder.configuarations.JwtTokenUtil;
import com.example.QrOrder.exceptions.ResourceNotFoundException;
import com.example.QrOrder.models.User;

import com.example.QrOrder.reponses.UserResponse;
import com.example.QrOrder.repository.UserRepository;
import com.example.QrOrder.service.IUserService;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    public String login(String gmail, String password) throws Exception {
        Optional<User> optionalUser = userRepository.findByEmail(gmail);
        if (optionalUser.isEmpty()) {
            throw new ResourceNotFoundException("Invalid phone number / password");
        }
        User existingUser = optionalUser.get();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                gmail, password,
                existingUser.getAuthorities() // Role user
        );  // tạo ra đôi tượng authenticationToken
        //authenticate with Java Spring security
        authenticationManager.authenticate(authenticationToken);
        return jwtTokenUtil.generateToken(existingUser.getEmail());
    }


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
        return userRepository.findAll();
    }

    @Override
    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
    }
}
