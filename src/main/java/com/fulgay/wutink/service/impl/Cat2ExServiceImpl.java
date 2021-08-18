package com.fulgay.wutink.service.impl;

import com.fulgay.wutink.dao.Cat2ExDao;
import com.fulgay.wutink.domain.Cat2Ex;
import com.fulgay.wutink.domain.Category;
import com.fulgay.wutink.domain.Experience;
import com.fulgay.wutink.service.Cat2ExService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Cat2ExServiceImpl implements Cat2ExService {
    @Autowired
    private Cat2ExDao cat2ExDao;

    @Override
    public Long save(Cat2Ex cat2Ex) {
        return cat2ExDao.save(cat2Ex);
    }

    @Override
    public List<Cat2Ex> findAll() {

        return cat2ExDao.findAll();
    }

    @Override
    public Cat2Ex findById(Long id) {
        return cat2ExDao.findById(id);
    }

    @Override
    public void update(Cat2Ex obj) {
        cat2ExDao.update(obj);
    }

    @Override
    public void delete(Cat2Ex obj) {
        cat2ExDao.delete(obj);
    }

    @Override
    public List<Experience> findExperienceByCategoryId(Long id) {
        return cat2ExDao.findExperienceByCategoryId(id);
    }

    @Override
    public void deleteRelByExperience(Experience experience) {
        cat2ExDao.deleteRelByExperience(experience);
    }

    @Override
    public List<Cat2Ex> findAllByExperienceAndCategory(Experience experience, Category category) {
       return cat2ExDao.findByExperienceAndCategory(experience,category);
    }

    @Override
    public List<Cat2Ex> findAllByExperience(Experience experience) {
        return cat2ExDao.findAllByExperience(experience);
    }
}
