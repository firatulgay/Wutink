package com.fulgay.bilenyum.utils.validators;

import com.fulgay.bilenyum.domain.Category;
import com.fulgay.bilenyum.service.impl.CategoryServiceImpl;
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
