package com.dbarvekar.blog.controllers;

import com.dbarvekar.blog.payloads.CategoryDTO;
import com.dbarvekar.blog.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    //POST create category
    @PostMapping("/create")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
        CategoryDTO savedCategory = categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    //PUT update category
    @PutMapping("/update")
    public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody  CategoryDTO categoryDTO){
        CategoryDTO savedCategoryDTO = categoryService.updateCategory(categoryDTO);
        return ResponseEntity.ok(savedCategoryDTO);
    }

    //GET all category
    @GetMapping("/get-all-category")
    public ResponseEntity<List<CategoryDTO>> getAllCategory(){
        List<CategoryDTO> allCategory = categoryService.getAllCategory();
        return new ResponseEntity<>(allCategory, HttpStatus.OK);
    }

    //GET category by id
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<CategoryDTO> getById(@PathVariable("id") Long id){
        CategoryDTO categoryById = categoryService.getCategoryById(id);
        return new ResponseEntity<CategoryDTO>(categoryById, HttpStatus.OK);
    }

    //DELETE category
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteCategory(@PathVariable("id") Long id){
        return new ResponseEntity<>(categoryService.deleteCategory(id), HttpStatus.OK);
    }
}
