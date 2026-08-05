package com.farrukh.ecommerce.user.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.farrukh.ecommerce.user.entity.User;


public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByEmail(String email);
}
