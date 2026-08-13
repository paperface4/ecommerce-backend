package com.farrukh.ecommerce.category.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCategoryRequest {
     @Pattern(
        regexp = ".*\\S.*",
        message = "Category name must not be blank"
    )
    private String name;
    private String description;
}
