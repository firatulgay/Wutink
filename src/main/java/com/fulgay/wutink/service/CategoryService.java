package com.fulgay.wutink.service;

import com.fulgay.wutink.domain.Category;

public interface CategoryService extends BaseService<Category> {
    public Category findCategoryByName(String categoryName);

}
