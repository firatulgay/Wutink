package com.fulgay.wutink.dao.impl;

import com.fulgay.wutink.dao.BaseDao;
import com.fulgay.wutink.domain.Experience;

import java.util.List;

/**
 * @author Fırat ÜLGAY
 * @since 29.01.2021
 */

public abstract class ExperienceDao extends BaseDao<Experience> {

    public ExperienceDao() {
        super(Experience.class);
    }

    public abstract List<Experience> findAllExperiencesByCategoryId(Long id);

    public abstract List<Experience> findExperienceByHeader(String header);

    public abstract List<Experience> findAllExperiencesByUserName(String userName);


    }
