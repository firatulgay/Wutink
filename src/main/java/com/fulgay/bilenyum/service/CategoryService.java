package com.fulgay.bilenyum.service;

import com.fulgay.bilenyum.domain.Category;

public interface CategoryService extends BaseService<Category> {
    public Category findCategoryByName(String categoryName);

}
