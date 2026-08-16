package com.farrukh.ecommerce.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class CreateProductResponse {
    private Long id;
    private String name;
    private String description;
    private String brand;
    private BigDecimal price;
    private Long categoryId;
    private String categoryName;
    private boolean active=true;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
