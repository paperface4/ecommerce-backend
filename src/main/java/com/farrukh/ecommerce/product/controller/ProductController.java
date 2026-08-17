package com.farrukh.ecommerce.product.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import com.farrukh.ecommerce.product.dto.CreateProductRequest;
import com.farrukh.ecommerce.product.dto.CreateProductResponse;
import com.farrukh.ecommerce.product.dto.ProductResponse;
import com.farrukh.ecommerce.product.service.ProductService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;




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

    @GetMapping()
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> response= productService.getAllProducts();
         return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        ProductResponse response=productService.getProductById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
    
    
}
