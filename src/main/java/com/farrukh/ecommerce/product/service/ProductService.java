package com.farrukh.ecommerce.product.service;

import org.springframework.stereotype.Service;

import com.farrukh.ecommerce.category.repository.CategoryRepository;
import com.farrukh.ecommerce.exception.CategoryNotFoundException;
import com.farrukh.ecommerce.product.dto.CreateProductRequest;
import com.farrukh.ecommerce.product.dto.CreateProductResponse;
import com.farrukh.ecommerce.product.repository.ProductRepository;
import com.farrukh.ecommerce.category.entity.Category;
import com.farrukh.ecommerce.product.entity.Product;

@Service
public class ProductService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public ProductService(CategoryRepository categoryRepository, ProductRepository productRepository){
        this.categoryRepository=categoryRepository;
        this.productRepository=productRepository;
    }

    public CreateProductResponse createProduct(CreateProductRequest request) {
        Long categoryId=request.getCategoryId();
        Category category=categoryRepository.findById(categoryId).orElseThrow(()->new CategoryNotFoundException("No category with this id exists"));
        Product product=new Product();
        product.setName(request.getName());
        product.setBrand(request.getBrand());
        product.setDescription(request.getDescription());
        product.setCategory(category);
        product.setPrice(request.getPrice());
        Product savedProduct=productRepository.save(product);
        return new CreateProductResponse(
            savedProduct.getId(),
            savedProduct.getName(),
            savedProduct.getDescription(),
            savedProduct.getBrand(),
            savedProduct.getPrice(),
            savedProduct.getCategory().getId(),
            savedProduct.getCategory().getName(),
            savedProduct.isActive(),
            savedProduct.getCreatedAt(),
            savedProduct.getUpdatedAt()
        );
    }
}
