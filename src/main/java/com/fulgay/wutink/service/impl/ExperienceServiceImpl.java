package com.fulgay.wutink.service.impl;

import com.fulgay.wutink.dao.ExperienceDao;
import com.fulgay.wutink.domain.Experience;
import com.fulgay.wutink.service.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ExperienceServiceImpl implements ExperienceService {

    @Autowired
    private ExperienceDao experienceDao;

    @Transactional(rollbackFor = Exception.class)
    public Long save(Experience experience) {
        experience.setCreationTime(new Date());
        return experienceDao.save(experience).getId();
    }

    @Override
    public List<Experience> findAll() {
        return experienceDao.findAll();    }

    @Override
    public Experience findById(Long id) {
        return experienceDao.findOne(id);    }

    @Override
    public void update(Experience obj) {
        obj.setModifiedTime(new Date());
        experienceDao.save(obj);
    }

    @Override
    public void delete(Experience obj) {
        experienceDao.delete(obj);
    }

    @Override
    public List<Experience> findExperienceByHeader(String experienceName) {
        return experienceDao.findExperienceByHeader(experienceName);
    }

    @Override
    public List<Experience> findAllExperiencesByUsername(String username) {
        return experienceDao.findAllExperienceByUserName(username);
    }

}
