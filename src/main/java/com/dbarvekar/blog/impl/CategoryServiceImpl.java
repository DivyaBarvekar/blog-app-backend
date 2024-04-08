package com.dbarvekar.blog.impl;

import com.dbarvekar.blog.entities.Category;
import com.dbarvekar.blog.exceptions.ResourceNotFoundException;
import com.dbarvekar.blog.payloads.CategoryDTO;
import com.dbarvekar.blog.repositories.CategoryRepository;
import com.dbarvekar.blog.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = convertToEntity(categoryDTO);
        category = categoryRepository.save(category);
        return convertToDTO(category);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO) {
        Category category = categoryRepository.findById(categoryDTO.getId()).orElseThrow(() -> {
            return new ResourceNotFoundException("User not found with id %d".formatted(categoryDTO.getId()));
        });
        Category category1 = convertToEntity(categoryDTO);
        BeanUtils.copyProperties(category1, category, "id");
        category = categoryRepository.save(category);
        return convertToDTO(category);
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> {
            return new ResourceNotFoundException("User not found with id %d".formatted(id));
        });
        return convertToDTO(category);
    }

    @Override
    public List<CategoryDTO> getAllCategory() {
        return categoryRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public boolean deleteCategory(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> {
            return new ResourceNotFoundException("User not found with id %d".formatted(id));
        });
        categoryRepository.deleteById(category.getId());
        return true;
    }

    private Category convertToEntity(CategoryDTO categoryDTO){
        return modelMapper.map(categoryDTO, Category.class);
    }

    private CategoryDTO convertToDTO(Category category){
        return modelMapper.map(category,CategoryDTO.class);
    }
}
