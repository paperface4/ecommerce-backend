package com.farrukh.ecommerce.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.farrukh.ecommerce.auth.service.AuthService;

import jakarta.validation.Valid;

import com.farrukh.ecommerce.auth.dto.RegisterRequest;
import com.farrukh.ecommerce.auth.dto.RegisterResponse;
import com.farrukh.ecommerce.auth.dto.LoginRequest;
import com.farrukh.ecommerce.auth.dto.LoginResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
        private final AuthService authService;

        public AuthController(AuthService authService){
            this.authService=authService;
        }
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> registerUser(@Valid @RequestBody RegisterRequest request) {
        RegisterResponse response = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED)
              .body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginRequest request) {  
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}
