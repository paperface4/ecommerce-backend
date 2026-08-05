package com.farrukh.ecommerce.user.entity;


import java.time.LocalDateTime;

import lombok.Builder;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(unique = true, nullable = false, length = 100)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String role;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
}
