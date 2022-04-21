package com.fulgay.wutink.converter;

import com.fulgay.wutink.domain.Category;
import com.fulgay.wutink.dtos.CategoryDropdownDto;
import org.springframework.stereotype.Component;

@Component
public class CategoryDropDownDtoConverter extends Converter<Category, CategoryDropdownDto> {

    @Override
    public CategoryDropdownDto convert(Category source) {

        CategoryDropdownDto target = new CategoryDropdownDto();
        target.setItem_id(source.getId());
        target.setItem_text(source.getName());

        return target;
    }
}
