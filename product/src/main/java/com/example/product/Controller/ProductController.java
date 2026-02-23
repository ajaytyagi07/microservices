package com.example.product.Controller;

import com.example.product.Config.CustomUserPrincipal;
import com.example.product.Entity.Product;
import com.example.product.Service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping
    public ResponseEntity<Map<String, Object>> create(
            @RequestBody Product product,
            @AuthenticationPrincipal CustomUserPrincipal user) {

        product.setCreatedBy(user.getId());
        return service.create(product);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable Long id,
                                                      @RequestBody Product product) {
        return service.update(id, product);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {
        return service.delete(id);
    }
}