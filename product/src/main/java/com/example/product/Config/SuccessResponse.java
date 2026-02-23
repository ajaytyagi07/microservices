package com.example.product.Config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.product.Entity.Product;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
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
   public static ResponseEntity<Map<String, Object>> build(String message, Product product) {
    Map<String, Object> response = new HashMap<>();
    response.put("timestamp", LocalDateTime.now());
    response.put("status", 200);
    response.put("message", message);

    // Add user details
    Map<String, Object> productData = new HashMap<>();
    productData.put("id", product.getId());
    productData.put("name", product.getName());
    productData.put("description", product.getDescription());
    productData.put("price", product.getPrice());
    productData.put("createdBy", product.getCreatedBy());
    response.put("product", productData);

    return new ResponseEntity<>(response, HttpStatus.OK);
}
 public static ResponseEntity<Map<String, Object>> build(String message,   List<Product>  product) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", 200);
        response.put("message", message);
        response.put("data", product);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}