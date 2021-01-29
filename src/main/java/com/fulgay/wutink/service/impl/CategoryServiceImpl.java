package com.fulgay.wutink.service.impl;

import com.fulgay.wutink.dao.CategoryDao;
import com.fulgay.wutink.domain.Category;
import com.fulgay.wutink.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public Long save(Category category) {
        return categoryDao.save(category);
    }

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryDao.findById(id);
    }

    @Override
    public void update(Category obj) {

    }

    @Override
    public Category findCategoryByName(String categoryName) {
        return categoryDao.findCategoryByName(categoryName);
    }
}
