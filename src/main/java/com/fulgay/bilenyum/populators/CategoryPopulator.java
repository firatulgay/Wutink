package com.fulgay.bilenyum.populators;

import com.fulgay.bilenyum.domain.Category;
import com.fulgay.bilenyum.dtos.CategoryDto;
import org.springframework.stereotype.Component;

@Component
public class CategoryPopulator implements Populator<CategoryDto, Category> {

    @Override
    public void populate(CategoryDto source,Category category) {
        if (source != null) {
            category.setId(source.getId());
            category.setName(source.getName());
        }
    }
}
