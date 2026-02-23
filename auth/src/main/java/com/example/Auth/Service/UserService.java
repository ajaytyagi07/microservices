package com.example.Auth.Service;

import com.example.Auth.Config.*;
import com.example.Auth.Entity.User;
import com.example.Auth.Repository.UserRepository;

import io.jsonwebtoken.*;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service

public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

   public ResponseEntity<Map<String, Object>> register(User request) {
    
       if(request.getName() == null || request.getEmail() == null || request.getPassword() == null) {
        throw new RuntimeException("All fields are required");
    }

    if (userRepository.findByEmail(request.getEmail()).isPresent()) {
        throw new RuntimeException("Email already exists");
    }

    User user = new User();
    user.setName(request.getName());
    user.setEmail(request.getEmail());
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    user.setRole("USER");

    userRepository.save(user); 
    return SuccessResponse.build("User Registered Successfully",user);

    
}
    
   public ResponseEntity<Map<String, Object>> login(User request) {
    if(request.getEmail() == null || request.getPassword() == null) {
        throw new RuntimeException("Email and password are required");
    }
    User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("User not found"));

    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
        throw new RuntimeException("Invalid password");
    }

    String token = jwtUtil.generateToken(user.getEmail(), user.getRole(), user.getId());
    return SuccessResponse.build("Login successful", user,token);
}
 public ResponseEntity<Map<String, Object>> validateToken(String token) {
        try {
            Claims claims = jwtUtil.validateToken(token);

            Map<String, Object> data = new HashMap<>();
            data.put("email", claims.getSubject());
            data.put("role", claims.get("role", String.class));

            return SuccessResponse.build("Token is valid", data);

        } catch (ExpiredJwtException ex) {
            throw new RuntimeException("Token has expired");
        } catch (SecurityException  ex) {
            throw new RuntimeException("Invalid token signature");
        } catch (Exception ex) {
            throw new RuntimeException("Token validation failed: " + ex.getMessage());
        }
    }

}
