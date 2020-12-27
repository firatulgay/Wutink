package com.fulgay.bilenyum.populators;

import com.fulgay.bilenyum.domain.Category;
import com.fulgay.bilenyum.dtos.CategoryDto;
import org.springframework.stereotype.Component;

@Component
public class CategoryPopulator implements Populator<CategoryDto, Category> {

    private Category category;

    @Override
    public Category populate(CategoryDto source) {
        category = new Category();
        if (source != null) {
            category.setId(source.getId());
            category.setName(source.getName());
        }
        return category;
    }
}
