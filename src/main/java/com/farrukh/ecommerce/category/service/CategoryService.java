package com.farrukh.ecommerce.category.service;
import org.springframework.stereotype.Service;

import com.farrukh.ecommerce.category.dto.CreateCategoryRequest;
import com.farrukh.ecommerce.category.dto.CreateCategoryResponse;
import com.farrukh.ecommerce.category.entity.Category;
import com.farrukh.ecommerce.category.repository.CategoryRepository;
import com.farrukh.ecommerce.exception.CategoryAlreadyExistsException;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
public CreateCategoryResponse createCategory(CreateCategoryRequest request) {

    String normalizedName =request.getName().trim().toLowerCase();

    if (categoryRepository.existsByName(normalizedName)) {
        throw new CategoryAlreadyExistsException(
                "Category already exists"
        );
    }
    Category category=new Category();
    category.setName(normalizedName);
    category.setDescription(request.getDescription());
    Category savedCategory = categoryRepository.save(category);
   return new CreateCategoryResponse(
        savedCategory.getId(),
        savedCategory.getName(),
        savedCategory.getDescription(),
        savedCategory.getCreatedAt(),
        savedCategory.getUpdatedAt()
);
    
}
}
