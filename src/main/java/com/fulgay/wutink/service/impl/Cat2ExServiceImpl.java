package com.fulgay.wutink.service.impl;

import com.fulgay.wutink.dtos.ExperienceDto;
import com.fulgay.wutink.repository.Cat2ExRepository;
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
    private Cat2ExRepository cat2ExRepository;

    @Override
    public Long save(Cat2Ex cat2Ex) {
        return cat2ExRepository.save(cat2Ex).getId();
    }

    @Override
    public List<Cat2Ex> findAll() {
        return (List<Cat2Ex>) cat2ExRepository.findAll();
    }

    @Override
    public Cat2Ex findById(Long id) {
        return cat2ExRepository.findById(id).get();
    }

    @Override
    public void update(Cat2Ex obj) {
        cat2ExRepository.save(obj);
    }

    @Override
    public void delete(Cat2Ex obj) {
        cat2ExRepository.delete(obj);
    }

    @Override
    public List<Experience> findExperienceByCategoryId(Long id) {
        return cat2ExRepository.findExperienceByCategoryId(id);
    }

    @Override
    public void deleteRelByExperience(Experience experience) {
        cat2ExRepository.deleteRelByExperience(experience);
    }

    @Override
    public List<Cat2Ex> findAllByExperienceAndCategory(Experience experience, Category category) {
       return cat2ExRepository.findByExperienceAndCategory(experience,category);
    }

    @Override
    public List<Cat2Ex> findAllByExperience(Experience experience) {
        return cat2ExRepository.findAllByExperience(experience);
    }

    @Override
    public List<ExperienceDto> findExperiencesByCategoryIdWithLikeAndCommentCount(Long id) {
        return cat2ExRepository.findExperiencesByCategoryIdWithLikeAndCommentCount(id);
    }
}
