package com.example.product.Config;

public class CustomUserPrincipal {
    private Long id;
    private String email;
    private String role;

    public CustomUserPrincipal(Long id, String email, String role) {
        this.id = id;
        this.email = email;
        this.role = role;
    }

    public Long getId() { return id; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
}