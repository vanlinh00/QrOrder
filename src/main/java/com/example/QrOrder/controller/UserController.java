package com.example.QrOrder.controller;

import com.example.QrOrder.models.User;
import com.example.QrOrder.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/user")
@AllArgsConstructor
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestParam String gmail, @RequestParam String password) throws Exception {
        String Token = userService.login(gmail, password);
        return Token;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() throws Exception {
        var authencation = SecurityContextHolder.getContext().getAuthentication();
        log.info("UserName {}", authencation.getName());
        authencation.getAuthorities().forEach(grantedAuthority -> log.info(
                grantedAuthority.getAuthority()
        ));
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
