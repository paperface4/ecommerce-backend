package com.farrukh.ecommerce.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farrukh.ecommerce.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    
}  
