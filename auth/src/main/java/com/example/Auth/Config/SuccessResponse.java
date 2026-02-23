package com.example.Auth.Config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.Auth.Entity.User;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class SuccessResponse {

    public static ResponseEntity<Map<String, Object>> build(String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", 200);
        response.put("message", message);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    public static ResponseEntity<Map<String, Object>> build(String message, Map<String, Object> data) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", 200);
        response.put("message", message);
        response.put("data", data);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
   public static ResponseEntity<Map<String, Object>> build(String message, User user) {
    Map<String, Object> response = new HashMap<>();
    response.put("timestamp", LocalDateTime.now());
    response.put("status", 200);
    response.put("message", message);

    // Add user details
    Map<String, Object> userData = new HashMap<>();
    userData.put("email", user.getEmail());
    userData.put("role", user.getRole());
    userData.put("name", user.getName());

    response.put("user", userData);

    return new ResponseEntity<>(response, HttpStatus.OK);
}
   public static ResponseEntity<Map<String, Object>> build(String message, User user, String token) {
    Map<String, Object> response = new HashMap<>();
    response.put("timestamp", LocalDateTime.now());
    response.put("status", 200);
    response.put("message", message);

    // Add user details
    Map<String, Object> userData = new HashMap<>();
    userData.put("email", user.getEmail());
    userData.put("role", user.getRole());
    userData.put("name", user.getName());

    response.put("user", userData);
    response.put("token", token);

    return new ResponseEntity<>(response, HttpStatus.OK);
}
}