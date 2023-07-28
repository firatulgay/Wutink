package com.fulgay.wutink.service.impl;

import com.fulgay.wutink.repository.ExperienceRepository;
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
    private ExperienceRepository experienceRepository;

    @Transactional(rollbackFor = Exception.class)
    public Long save(Experience experience) {
        experience.setCreationTime(new Date());
        return experienceRepository.save(experience).getId();
    }

    @Override
    public List<Experience> findAll() {
        return (List<Experience>) experienceRepository.findAll();    }

    @Override
    public Experience findById(Long id) {
        return experienceRepository.findById(id).get();    }

    @Override
    public void update(Experience obj) {
        obj.setModifiedTime(new Date());
        experienceRepository.save(obj);
    }

    @Override
    public void delete(Experience obj) {
        experienceRepository.delete(obj);
    }

    @Override
    public List<Experience> findExperienceByHeader(String experienceName) {
        return experienceRepository.findExperienceByHeader(experienceName);
    }

    @Override
    public List<Experience> findAllExperiencesByUsername(String username) {
        return experienceRepository.findAllExperienceByUserName(username);
    }

}
