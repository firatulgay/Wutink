package com.fulgay.bilenyum.utils.validators;

import com.fulgay.bilenyum.domain.Category;
import com.fulgay.bilenyum.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryValidator {

    @Autowired
    CategoryService categoryService;

    public boolean validateCategoryByCategoryName(String categoryName)  {

        Category Category = categoryService.findCategoryByName(categoryName);

        if (Category != null){
            return false;
        }else {
            return true;
        }
    }
}
