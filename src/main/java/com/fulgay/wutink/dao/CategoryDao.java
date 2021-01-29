package com.fulgay.wutink.dao;

import com.fulgay.wutink.dao.BaseDao;
import com.fulgay.wutink.domain.Category;

/**
 * @author Fırat ÜLGAY
 * @since 30.01.2021
 */

public abstract class CategoryDao extends BaseDao<Category> {

    public CategoryDao() {
        super(Category.class);
    }

    public abstract Category findCategoryByName(String name);

}
