package com.fulgay.wutink.populators;

import com.fulgay.wutink.domain.Category;
import com.fulgay.wutink.dtos.CategoryDto;
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
