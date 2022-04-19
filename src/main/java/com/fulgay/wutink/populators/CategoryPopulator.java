package com.fulgay.wutink.populators;

import com.fulgay.wutink.domain.Category;
import com.fulgay.wutink.dtos.CategoryDto;
import org.springframework.stereotype.Component;

@Component
public class CategoryPopulator implements Populator<CategoryDto, Category> {

    @Override
    public void populate(CategoryDto source,Category category) {
        if (source != null) {
            category.setId(source.getId());
            category.setName(source.getName());
            category.setIconBase64(source.getIcon());
        }
    }
}
