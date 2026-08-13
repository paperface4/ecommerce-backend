package com.farrukh.ecommerce.category.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.farrukh.ecommerce.category.dto.CreateCategoryRequest;
import com.farrukh.ecommerce.category.dto.CreateCategoryResponse;
import com.farrukh.ecommerce.category.dto.UpdateCategoryRequest;
import com.farrukh.ecommerce.category.entity.Category;
import com.farrukh.ecommerce.category.repository.CategoryRepository;
import com.farrukh.ecommerce.exception.CategoryAlreadyExistsException;
import com.farrukh.ecommerce.exception.CategoryNotFoundException;

import jakarta.validation.constraints.Null;

import com.farrukh.ecommerce.category.dto.CategoryResponse;
import com.farrukh.ecommerce.category.dto.UpdateCategoryRequest;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
public CreateCategoryResponse createCategory(CreateCategoryRequest request) {

    String normalizedName =request.getName().trim().toLowerCase();

    if (categoryRepository.existsByName(normalizedName)) {
        throw new CategoryAlreadyExistsException("Category already exists");
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

public List<CategoryResponse> getAllCategories(){
    List<Category> categories = categoryRepository.findAll();
    List<CategoryResponse> responses=new ArrayList<>();
    for(Category category:categories){
        CategoryResponse response =new CategoryResponse();
                response.setId(category.getId());
                response.setName(category.getName());
                response.setDescription(category.getDescription());
                response.setCreatedAt(category.getCreatedAt());
                response.setUpdatedAt(category.getUpdatedAt());
                 responses.add(response);
    }
    return responses;
}

public CategoryResponse getCategoryById(Long id){
   Category category= categoryRepository.findById(id).orElseThrow(()->new CategoryNotFoundException("No category with this id exists"));
    CategoryResponse response=new CategoryResponse();
   response.setId(category.getId());
                response.setName(category.getName());
                response.setDescription(category.getDescription());
                response.setCreatedAt(category.getCreatedAt());
                response.setUpdatedAt(category.getUpdatedAt());
                return response;
}

 public CategoryResponse updateCategory(Long id, UpdateCategoryRequest request){
    Category category =categoryRepository.findById(id).orElseThrow(()-> new CategoryNotFoundException("No category with this id exits"));
    if(request.getName()!= null){
        String normalizedName=request.getName().trim().toLowerCase();
        if(!normalizedName.equals(category.getName())){
            if (categoryRepository.existsByName(normalizedName)) {
    throw new CategoryAlreadyExistsException(
            "Category with this name already exists"
    );
}
        }
        category.setName(normalizedName);
    }
    if(request.getDescription()!= null){
        category.setDescription(request.getDescription());
    }
    Category updatedCategory = categoryRepository.save(category);
    CategoryResponse response= new CategoryResponse();
       response.setId(updatedCategory.getId());
                response.setName(updatedCategory.getName());
                response.setDescription(updatedCategory.getDescription());
                response.setCreatedAt(updatedCategory.getCreatedAt());
                response.setUpdatedAt(updatedCategory.getUpdatedAt());
                return response;

 }
}
