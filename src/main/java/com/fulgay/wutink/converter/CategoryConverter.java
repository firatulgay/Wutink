package com.fulgay.wutink.converter;

import com.fulgay.wutink.domain.Category;
import com.fulgay.wutink.dtos.CategoryDto;
import com.fulgay.wutink.populators.CategoryPopulator;
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
