package com.farrukh.ecommerce.category.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.farrukh.ecommerce.category.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    public boolean existsByName(String name);
}
