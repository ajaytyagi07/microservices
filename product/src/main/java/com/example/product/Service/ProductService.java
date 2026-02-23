package com.example.product.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.product.Config.GlobalExceptionHandler;
import com.example.product.Config.SuccessResponse;
import com.example.product.Entity.Product;
import com.example.product.Repository.ProductRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    // CREATE
    public ResponseEntity<Map<String, Object>> create(Product product) {
        validateProduct(product); 
        Product savedProduct = repository.save(product);
        return SuccessResponse.build("Product created successfully", savedProduct);
    }

    // READ ALL
    public ResponseEntity<Map<String, Object>> getAll() {
        List<Product> products = repository.findAll();
        return SuccessResponse.build("Products retrieved successfully", products);
    }

    // READ BY ID
    public ResponseEntity<Map<String, Object>> getById(Long id) {
        Optional<Product> product = repository.findById(id);
        if (product.isPresent()) {
            return SuccessResponse.build("Product retrieved successfully", product.get());
        } else {
            throw new RuntimeException("Product not found");
        }
    }

    // UPDATE
  public ResponseEntity<Map<String, Object>> update(Long id, Product product) {
    return repository.findById(id).map(existing -> {
        // Only update fields if they are provided
        if (product.getName() != null && !product.getName().isBlank()) {
            existing.setName(product.getName());
        }
        if (product.getDescription() != null && !product.getDescription().isBlank()) {
            existing.setDescription(product.getDescription());
        }
        if (product.getPrice() > 0) {
            existing.setPrice(product.getPrice());
        }
   
        Product updated = repository.save(existing);
        return SuccessResponse.build("Product updated successfully", updated);
    }).orElseThrow(() -> new RuntimeException("Product not found"));
}


    // DELETE
   public ResponseEntity<Map<String, Object>> delete(Long id) {
    return repository.findById(id).map(product -> {
        repository.delete(product);
        return SuccessResponse.build("Product deleted successfully", product);
    }).orElseThrow(() -> new RuntimeException("Product with ID " + id + " not found"));
}


    private void validateProduct(Product product) {
    if (product.getName() == null || product.getName().isBlank()) {
        throw new RuntimeException("Product name is required");
    }
    if (product.getDescription() == null || product.getDescription().isBlank()) {
        throw new RuntimeException("Product description is required");
    }
    if (product.getPrice() <= 0) {
        throw new RuntimeException("Product price must be greater than 0");
    }
   
}
}
