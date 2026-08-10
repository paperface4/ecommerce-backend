package com.farrukh.ecommerce.category.controller;

import org.springframework.web.bind.annotation.RestController;

import com.farrukh.ecommerce.category.dto.CreateCategoryRequest;
import com.farrukh.ecommerce.category.dto.CreateCategoryResponse;
import com.farrukh.ecommerce.category.service.CategoryService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService=categoryService;
    }
    @PostMapping
    public ResponseEntity<CreateCategoryResponse> createCategory(@Valid @RequestBody CreateCategoryRequest request) {
       CreateCategoryResponse response= categoryService.createCategory(request);
        return ResponseEntity.status(HttpStatus.CREATED)
              .body(response);
    }
    
    
}
