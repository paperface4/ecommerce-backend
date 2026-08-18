package com.farrukh.ecommerce.product.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import com.farrukh.ecommerce.category.repository.CategoryRepository;
import com.farrukh.ecommerce.exception.CategoryNotFoundException;
import com.farrukh.ecommerce.exception.ProductNotFoundException;
import com.farrukh.ecommerce.product.dto.CreateProductRequest;
import com.farrukh.ecommerce.product.dto.CreateProductResponse;
import com.farrukh.ecommerce.product.dto.ProductResponse;
import com.farrukh.ecommerce.product.repository.ProductRepository;
import com.farrukh.ecommerce.product.dto.UpdateProductRequest;

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

    public List<ProductResponse> getAllProducts(){
        List<Product> products=productRepository.findAll();
        List<ProductResponse> responses=new ArrayList<>();

        for(Product product:products){
            ProductResponse response=new ProductResponse();
            response.setId(product.getId());
            response.setName(product.getName());
            response.setDescription(product.getDescription());
            response.setBrand(product.getBrand());
            response.setPrice(product.getPrice());
            response.setCategoryId(product.getCategory().getId());
            response.setCategoryName(product.getCategory().getName());
            response.setActive(product.isActive());
            response.setCreatedAt(product.getCreatedAt());
            response.setUpdatedAt(product.getUpdatedAt());
            responses.add(response);
        }
        return responses;
    }

    public ProductResponse getProductById(Long id){
        Product product = productRepository.findById(id).orElseThrow(()->new ProductNotFoundException("No product with this Id exists"));
        ProductResponse response=new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setBrand(product.getBrand());
        response.setPrice(product.getPrice());
        response.setCategoryId(product.getCategory().getId());
        response.setCategoryName(product.getCategory().getName());
        response.setActive(product.isActive());
        response.setCreatedAt(product.getCreatedAt());
        response.setUpdatedAt(product.getUpdatedAt());
        return response;
    }

    public ProductResponse updateProduct(Long id, UpdateProductRequest request){
        Product product =productRepository.findById(id).orElseThrow(()->new ProductNotFoundException("No product with this Id exists"));
        if(request.getName()!=null){
            product.setName(request.getName());
        }
        if(request.getDescription()!=null){
            product.setDescription(request.getDescription());
        }
        if(request.getPrice()!=null){
            product.setPrice(request.getPrice());
        }
        if(request.getBrand()!=null){
            product.setBrand(request.getBrand());
        }
        if(request.getCategoryId()!=null){
            Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(()-> new CategoryNotFoundException("no category with this id exists"));
            product.setCategory(category);
        }
        if (request.getActive() != null) {
           product.setActive(request.getActive());
        }
        Product updatedProduct = productRepository.save(product);
         ProductResponse response=new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setBrand(product.getBrand());
        response.setPrice(product.getPrice());
        response.setCategoryId(product.getCategory().getId());
        response.setCategoryName(product.getCategory().getName());
        response.setActive(product.isActive());
        response.setCreatedAt(product.getCreatedAt());
        response.setUpdatedAt(product.getUpdatedAt());
        return response;

    }
}
