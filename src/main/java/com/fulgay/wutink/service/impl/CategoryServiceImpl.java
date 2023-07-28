package com.fulgay.wutink.service.impl;

import com.fulgay.wutink.repository.CategoryRepository;
import com.fulgay.wutink.domain.Category;
import com.fulgay.wutink.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Long save(Category category) {
        return categoryRepository.save(category).getId();
    }

    @Override
    public List<Category> findAll() {
        return (List<Category>) categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).get();
    }

    @Override
    public void update(Category obj) {

    }

    @Override
    public void delete(Category obj) {

    }

    @Override
    public Category findCategoryByName(String categoryName) {
        return categoryRepository.findCategoryByName(categoryName);
    }
}
