package com.fulgay.bilenyum.populators;

import com.fulgay.bilenyum.domain.Category;
import com.fulgay.bilenyum.dtos.CategoryDto;
import org.springframework.stereotype.Component;

public class CategoryDtoPopulator implements Populator<Category,CategoryDto> {

    private CategoryDto categoryDto;

    @Override
    public CategoryDto populate(Category source) {
        categoryDto = new CategoryDto();

        if (source != null) {
            categoryDto.setName(source.getName());
            categoryDto.setId(source.getId());
        }
        return categoryDto;
    }
}
