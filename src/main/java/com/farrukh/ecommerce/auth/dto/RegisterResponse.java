package com.farrukh.ecommerce.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.time.LocalDateTime;
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
