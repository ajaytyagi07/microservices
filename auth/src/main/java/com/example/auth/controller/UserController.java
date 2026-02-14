package com.example.auth.controller;

import com.example.auth.model.User;
import com.example.auth.service.UserService;
import com.example.auth.config.JwtUtil;

import io.jsonwebtoken.Claims;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        user.setRole("USER");
        return service.register(user);
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody User user) {

        Optional<User> existingUser = service.login(user.getEmail(), user.getPassword());

        if (existingUser.isPresent()) {

            String token = jwtUtil.generateToken(
                    existingUser.get().getEmail(),
                    existingUser.get().getRole());

            return Map.of("token", token);
        }

        throw new RuntimeException("Invalid Credentials");
    }

    @GetMapping("/validate-token")
    public Map<String, String> validate(@RequestParam String token) {

        Claims claims = jwtUtil.validateToken(token);

        return Map.of(
                "email", claims.getSubject(),
                "role", claims.get("role", String.class));
    }
}
