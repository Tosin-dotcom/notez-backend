package com.tosin.notez.category;


import com.tosin.notez.Tables;
import com.tosin.notez.category.dto.CategoryDto;
import com.tosin.notez.tables.daos.CategoriesDao;
import com.tosin.notez.tables.pojos.Categories;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryRepository {

    @Value("${notez.cdn}")
    private String notezCdn;

    private final CategoriesDao categoriesDao;
    private final ModelMapper modelMapper;
    private final DSLContext dslContext;

    public CategoryDto saveCategory(CategoryDto categoryDto) {

        Categories categories = new Categories();
        categories.setId(UUID.randomUUID());
        categories.setName(categoryDto.getName());
        categories.setImageUrl(categoryDto.getFileDto().getUrl());
        categories.setCreatedAt(LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC));
        categories.setUpdatedAt(LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC));
        categoriesDao.insert(categories);

        return map(categories);
    }

    public CategoryDto map(Categories categories) {

        CategoryDto map = modelMapper.map(categories, CategoryDto.class);
        map.setImageUrl(String.format("%s/%s", notezCdn,categories.getImageUrl()));
        return map;
    }

    public List<CategoryDto> getAllCategories() {

        return dslContext.selectFrom(Tables.CATEGORIES)
                .fetchInto(Categories.class)
                .stream().map(this::map).toList();
    }

}
