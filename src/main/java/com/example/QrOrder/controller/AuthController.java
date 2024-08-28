package com.example.QrOrder.controller;

import com.example.QrOrder.configuarations.JwtTokenUtil;
import com.example.QrOrder.models.MenuItem;
import com.example.QrOrder.models.User;
import com.example.QrOrder.reponses.UserResponse;
import com.example.QrOrder.service.MenuItemService;
import com.example.QrOrder.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/user")
@AllArgsConstructor
public class AuthController {

    // private AuthenticationManager authenticationManager;
    // private UserDetailsService userDetailsService;
    private JwtTokenUtil jwtUtil;
    private UserServiceImpl userService;
    private MenuItemService menuService;

    @PostMapping("/login")
    public String authenticate(@RequestParam String gmail, @RequestParam String password) throws Exception {
        //authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        //  final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return jwtUtil.generateToken(gmail);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() throws Exception {
        System.out.println("getAllMenuItems");  // Log số 1 ra console
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
