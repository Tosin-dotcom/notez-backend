package com.tosin.notez.category;


import com.tosin.notez.category.dto.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {


    private final CategoryRepository categoryRepository;

    public CategoryDto createNewCategory(CategoryDto categoryDto) {

        return categoryRepository.saveCategory(categoryDto);
    }

    public List<CategoryDto> getAllCategories() {

        return categoryRepository.getAllCategories();
    }

}

