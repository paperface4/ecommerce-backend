package com.farrukh.ecommerce.product.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private String brand;
    private BigDecimal price;
    private Long categoryId;
    private String categoryName;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
