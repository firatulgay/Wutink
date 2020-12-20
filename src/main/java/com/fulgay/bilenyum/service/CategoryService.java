package com.fulgay.bilenyum.service;

import com.fulgay.bilenyum.dao.CategoryDao;
import com.fulgay.bilenyum.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryDao categoryDao;

    @Transactional(rollbackFor = Exception.class)
    public Long save(Category category) {
        try {
            Long id = categoryDao.save(category);
            return id;
        } catch (UnexpectedRollbackException e) {
            return null;
        }
    }

    public List<Category> findAllCategories(){
        return categoryDao.findAll();
    }

    public Category findCategoryById(Long id){
        return categoryDao.findById(id);
    }

    public Category findCategoryByName(String categoryName) {
       return categoryDao.findCategoryByName(categoryName);
    }
}
