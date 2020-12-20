package com.fulgay.bilenyum.populators;

import com.fulgay.bilenyum.domain.Category;
import com.fulgay.bilenyum.dtos.CategoryDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryPopulator {

    private List<CategoryDto> categoryDtoList;
    private Category category;
    private CategoryDto categoryDto;
    private List<Category> categoryList;

    public List<CategoryDto> populateCategoryDtoList(List<Category> categoryList) {
        if (categoryList != null && categoryList.size() > 0) {
            categoryDtoList = new ArrayList<>();

            for (Category category : categoryList) {
                CategoryDto categoryDto = populateCategoryDto(category);
                categoryDtoList.add(categoryDto);
            }
        }
        return categoryDtoList;
    }

    public CategoryDto populateCategoryDto(Category category) {
        categoryDto = new CategoryDto();

        if (category != null) {
            categoryDto.setName(category.getName());
            categoryDto.setId(category.getId());
        }
        return categoryDto;
    }

    public Category populateCategory(CategoryDto categoryDto) {
        category = new Category();
        if (categoryDto != null) {
            category.setId(categoryDto.getId());
            category.setName(categoryDto.getName());
        }
        return category;
    }

    public List<Category> populateCategoryList(List<CategoryDto> categoryDtoList) {
        if (categoryDtoList != null && categoryDtoList.size() > 0) {
            categoryList = new ArrayList<>();

            for (CategoryDto categoryDto : categoryDtoList) {
                Category category = populateCategory(categoryDto);
                this.categoryList.add(category);
            }
        }
        return categoryList;
    }

}
