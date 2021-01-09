package com.fulgay.bilenyum.populators;

import com.fulgay.bilenyum.domain.Category;
import com.fulgay.bilenyum.dtos.CategoryDto;
import org.springframework.stereotype.Component;

@Component
public class CategoryDtoPopulator implements Populator<Category,CategoryDto> {

    @Override
    public void populate(Category source,CategoryDto target) {

        if (source != null) {
            target.setName(source.getName());
            target.setId(source.getId());
        }
    }
}
