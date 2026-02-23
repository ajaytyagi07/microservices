package com.example.Auth.Controller;
import com.example.Auth.Service.*;


import com.example.Auth.Config.*;
import com.example.Auth.Entity.User;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService authService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody User request) {
        return authService.register(request);

    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody User request) {
       
        return authService.login(request);
    }

   @GetMapping("/validate-token")
public ResponseEntity<Map<String, Object>> validateToken(@RequestParam String token) {
return authService.validateToken(token);

}
}
