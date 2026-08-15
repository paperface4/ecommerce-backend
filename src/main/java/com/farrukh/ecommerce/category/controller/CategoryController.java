package com.farrukh.ecommerce.category.controller;

import org.springframework.web.bind.annotation.RestController;

import com.farrukh.ecommerce.category.dto.CategoryResponse;
import com.farrukh.ecommerce.category.dto.CreateCategoryRequest;
import com.farrukh.ecommerce.category.dto.CreateCategoryResponse;
import com.farrukh.ecommerce.category.dto.UpdateCategoryRequest;
import com.farrukh.ecommerce.category.service.CategoryService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    @GetMapping()
    public ResponseEntity<List<CategoryResponse>>getAllCategory() {
        List<CategoryResponse> response=categoryService.getAllCategories();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById( @PathVariable Long id) {
        CategoryResponse response =categoryService.getCategoryById(id);
            return ResponseEntity.ok(response);
        
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(@Valid @RequestBody UpdateCategoryRequest request,@PathVariable Long id){
        CategoryResponse response =categoryService.updateCategory(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
      categoryService.deleteCategory(id);
      return ResponseEntity.noContent().build();
}
    
    
    
}
