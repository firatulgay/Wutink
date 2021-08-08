package com.fulgay.wutink.service.impl;

import com.fulgay.wutink.dao.impl.ExperienceDaoImpl;
import com.fulgay.wutink.domain.Experience;
import com.fulgay.wutink.service.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ExperienceServiceImpl implements ExperienceService {

    @Autowired
    private ExperienceDaoImpl experienceDao;

    @Transactional(rollbackFor = Exception.class)
    public Long save(Experience experience) {
        return experienceDao.save(experience);
    }

    @Override
    public List<Experience> findAll() {
        return experienceDao.findAll();    }

    @Override
    public Experience findById(Long id) {
        return experienceDao.findById(id);    }

    @Override
    public void update(Experience obj) {

    }

    @Override
    public void delete(Experience obj) {

    }

    @Override
    public List<Experience> findExperienceByHeader(String experienceName) {
        return experienceDao.findExperienceByHeader(experienceName);
    }
}
