package com.farrukh.ecommerce.product.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farrukh.ecommerce.product.dto.CreateProductRequest;
import com.farrukh.ecommerce.product.dto.CreateProductResponse;
import com.farrukh.ecommerce.product.service.ProductService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/products")
public class ProductController {
    final private ProductService productService;
    public ProductController(ProductService productService){
        this.productService=productService;
    }
    
    @PostMapping()
    public ResponseEntity<CreateProductResponse> createProduct(@Valid @RequestBody CreateProductRequest request) { 
        CreateProductResponse response =productService.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
}
