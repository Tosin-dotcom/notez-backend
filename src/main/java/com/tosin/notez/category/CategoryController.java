package com.tosin.notez.category;


import com.tosin.notez.category.dto.CategoryDto;
import com.tosin.notez.model.Request;
import com.tosin.notez.model.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {


    private final CategoryService categoryService;


    @PostMapping
    public ResponseEntity<Response<CategoryDto>> createCategory(@RequestBody Request<CategoryDto> request) {

        CategoryDto savedCategory = categoryService.createNewCategory(request.getBody());
        Response<CategoryDto> response = Response.<CategoryDto>builder()
                .body(savedCategory)
                .status(true)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<Response<List<CategoryDto>>> getAllCategories() {

        List<CategoryDto> categoryDtoList = categoryService.getAllCategories();
        Response<List<CategoryDto>> response = Response.<List<CategoryDto>>builder()
                .body(categoryDtoList)
                .status(true)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
