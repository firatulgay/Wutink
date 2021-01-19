package com.fulgay.wutink.converter;

import com.fulgay.wutink.domain.Category;
import com.fulgay.wutink.dtos.CategoryDto;
import com.fulgay.wutink.populators.CategoryDtoPopulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryDtoConverter extends Converter<Category, CategoryDto> {

    @Autowired
    CategoryDtoPopulator categoryDtoPopulator;

    @Override
    public CategoryDto convert(Category source) {
        CategoryDto target = new CategoryDto();
        categoryDtoPopulator.populate(source,target);
        return target;
    }
}
