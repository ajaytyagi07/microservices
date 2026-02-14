package com.example.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.product.model.Product;
import com.example.product.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    // CREATE
    public Product save(Product product) {
        return repository.save(product);
    }

    // READ ALL
    public List<Product> getAll() {
        return repository.findAll();
    }

    // READ BY ID
    public Optional<Product> getById(Long id) {
        return repository.findById(id);
    }

    // UPDATE
    public Product update(Long id, Product product) {
        return repository.findById(id).map(existing -> {
            existing.setName(product.getName());
            existing.setDescription(product.getDescription());
            existing.setPrice(product.getPrice());
            return repository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    // DELETE
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
