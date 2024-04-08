package com.dbarvekar.blog.repositories;

import com.dbarvekar.blog.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
