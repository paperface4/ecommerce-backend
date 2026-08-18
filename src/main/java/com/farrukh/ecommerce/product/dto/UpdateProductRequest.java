package com.farrukh.ecommerce.product.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductRequest {
     @Pattern(
        regexp = ".*\\S.*",
        message = "Product name must not be blank"
    )
    private String name;
    private String description;
     @Pattern(
        regexp = ".*\\S.*",
        message = "Brand name must not be blank"
    )
    private String brand;
    @DecimalMin(
    value = "0.01",
    message = "Price must be greater than 0"
)
    private BigDecimal price;
    @Positive(message="Category Id must be positive")
    private Long categoryId;
    private Boolean active;
}
