package com.fulgay.bilenyum.service;

import com.fulgay.bilenyum.dao.ExperienceDao;
import com.fulgay.bilenyum.domain.Experience;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExperienceService {

    @Autowired
    ExperienceDao experienceDao;

    @Transactional(rollbackFor = Exception.class)
    public Long save(Experience experience) {
        try {
            Long id = experienceDao.save(experience);
            return id;
        } catch (UnexpectedRollbackException e) {
            return null;
        }
    }

    public List<Experience> findAllExperiences(){
        return experienceDao.findAll();
    }

    public Experience findExperienceById(Long id){
        return experienceDao.findById(id);
    }

    public List<Experience> findExperienceHeader(String experienceName) {
        return experienceDao.findExperienceByHeader(experienceName);
    }
}
