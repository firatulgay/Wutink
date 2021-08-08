package com.fulgay.wutink.dao;

import com.fulgay.wutink.domain.Like2Experience;

import java.util.List;

/**
 * @author Fırat ÜLGAY
 * @since 1.08.2021
 */

public abstract class Like2ExperienceDao extends BaseDao<Like2Experience> {
    public Like2ExperienceDao() {
        super(Like2Experience.class);
    }

    public abstract List<Like2Experience> findAllByExperienceId(Long experienceId);

    public abstract Like2Experience findByExperienceIdAndUsername(Long experienceId, String username);
}
