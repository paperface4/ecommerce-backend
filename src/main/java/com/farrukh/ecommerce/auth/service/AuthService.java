package com.farrukh.ecommerce.auth.service;

import org.springframework.stereotype.Service;

import com.farrukh.ecommerce.auth.dto.RegisterRequest;
import com.farrukh.ecommerce.user.repository.UserRepository;
import com.farrukh.ecommerce.auth.dto.RegisterResponse;
import java.time.LocalDateTime;
import com.farrukh.ecommerce.user.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
    }

    public RegisterResponse register(RegisterRequest request){
        if(!request.getPassword().equals(request.getConfirmPassword())){
            throw new IllegalArgumentException("Password and Confirm Password do not match");
        }
        String normalizedEmail = request.getEmail().trim().toLowerCase();

        if(userRepository.existsByEmail(normalizedEmail)){
            throw new IllegalArgumentException("Email already exists");
        }

        String encodedPassword= passwordEncoder.encode(request.getPassword());

       User user = User.builder()
        .firstName(request.getFirstName().trim())
        .lastName(request.getLastName().trim())
        .email(normalizedEmail)
        .password(encodedPassword)
        .role("ROLE_CUSTOMER")
        .createdAt(LocalDateTime.now())
        .updatedAt(LocalDateTime.now())
        .build();

User savedUser = userRepository.save(user);

        return RegisterResponse.builder()
                .id(savedUser.getId())
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .email(savedUser.getEmail())
                .role(savedUser.getRole())
                .createdAt(savedUser.getCreatedAt())
                .updatedAt(savedUser.getUpdatedAt())
                .build();

    }
}
