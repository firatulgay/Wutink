package com.fulgay.bilenyum.converter;

import com.fulgay.bilenyum.domain.Category;
import com.fulgay.bilenyum.dtos.CategoryDto;
import com.fulgay.bilenyum.populators.CategoryPopulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter extends Converter<CategoryDto, Category> {

    @Autowired
    CategoryPopulator categoryPopulator;

    @Override
    public Category convert(CategoryDto source) {
        Category target = new Category();
        categoryPopulator.populate(source,target);
        return target;
    }
}
