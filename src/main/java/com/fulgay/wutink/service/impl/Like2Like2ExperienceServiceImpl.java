package com.fulgay.wutink.service.impl;

import com.fulgay.wutink.dao.Like2ExperienceDao;
import com.fulgay.wutink.domain.Like2Experience;
import com.fulgay.wutink.service.Like2ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Fırat ÜLGAY
 * @since 1.08.2021
 */

@Service
public class Like2Like2ExperienceServiceImpl implements Like2ExperienceService {

    @Autowired
    private Like2ExperienceDao like2ExperienceDao;

    @Override
    public Long save(Like2Experience obj) {
        return like2ExperienceDao.save(obj);
    }

    @Override
    public List<Like2Experience> findAll() {
        return null;
    }

    @Override
    public Like2Experience findById(Long id) {
        return null;
    }

    @Override
    public void update(Like2Experience obj) {

    }

    @Override
    public void delete(Like2Experience obj) {
        like2ExperienceDao.delete(obj);
    }

    @Override
    public List<Like2Experience> findAllByExperienceId(Long experienceId) {
        return like2ExperienceDao.findAllByExperienceId(experienceId);
    }

    @Override
    public Like2Experience findByExperienceIdAndUsername(Long experienceId,String username) {
        return like2ExperienceDao.findByExperienceIdAndUsername(experienceId,username);
    }
}
