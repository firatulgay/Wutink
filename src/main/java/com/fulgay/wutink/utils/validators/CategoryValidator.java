package com.fulgay.wutink.utils.validators;

import com.fulgay.wutink.domain.Category;
import com.fulgay.wutink.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryValidator {

    @Autowired
    private CategoryServiceImpl categoryServiceImpl;

    public boolean validateIfCategoryExist(String categoryName)  {

        Category category = categoryServiceImpl.findCategoryByName(categoryName);
        return category != null ;
    }
}
