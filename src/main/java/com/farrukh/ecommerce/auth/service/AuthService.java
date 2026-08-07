package com.farrukh.ecommerce.auth.service;

import org.springframework.stereotype.Service;

import com.farrukh.ecommerce.auth.dto.LoginRequest;
import com.farrukh.ecommerce.auth.dto.LoginResponse;
import com.farrukh.ecommerce.auth.dto.RegisterRequest;
import com.farrukh.ecommerce.user.repository.UserRepository;
import com.farrukh.ecommerce.auth.dto.RegisterResponse;
import java.time.LocalDateTime;
import com.farrukh.ecommerce.user.entity.User;
import com.farrukh.ecommerce.role.Role;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.farrukh.ecommerce.exception.EmailAlreadyExistsException;
import com.farrukh.ecommerce.exception.PasswordMismatchException;
import com.farrukh.ecommerce.exception.InvalidCredentialsException;
import com.farrukh.ecommerce.security.JwtService;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder , JwtService jwtService){
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
        this.jwtService=jwtService;
    }

    public RegisterResponse register(RegisterRequest request){
        if(!request.getPassword().equals(request.getConfirmPassword())){
            throw new PasswordMismatchException("Password and Confirm Password do not match");
        }
        String normalizedEmail = request.getEmail().trim().toLowerCase();

        if(userRepository.existsByEmail(normalizedEmail)){
            throw new EmailAlreadyExistsException("Email already exists");
        }

        String encodedPassword= passwordEncoder.encode(request.getPassword());

       User user = User.builder()
        .firstName(request.getFirstName().trim())
        .lastName(request.getLastName().trim())
        .email(normalizedEmail)
        .password(encodedPassword)
        .role(Role.ROLE_CUSTOMER)
        .createdAt(LocalDateTime.now())
        .updatedAt(LocalDateTime.now())
        .build();

User savedUser = userRepository.save(user);

        return RegisterResponse.builder()
                .id(savedUser.getId())
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .email(savedUser.getEmail())
                .role(savedUser.getRole().name())
                .createdAt(savedUser.getCreatedAt())
                .updatedAt(savedUser.getUpdatedAt())
                .build();

    }

    public LoginResponse login(LoginRequest request){
        String normlizedEmail=request.getEmail().trim().toLowerCase();
        User user=userRepository.findByEmail(normlizedEmail)
                .orElseThrow(()->new InvalidCredentialsException("Invalid email or password"));
        if(!passwordEncoder.matches(request.getPassword(),user.getPassword())){
            throw new InvalidCredentialsException("Invalid email or password");
        }
        String token = jwtService.generateToken(user);
        return LoginResponse.builder()
        .token(token)
        .firstName(user.getFirstName())
        .lastName(user.getLastName())
        .email(user.getEmail())
        .role(user.getRole().name())
        .build();
    }
}
