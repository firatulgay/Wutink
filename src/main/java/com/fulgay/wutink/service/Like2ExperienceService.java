package com.fulgay.wutink.service;

import com.fulgay.wutink.domain.Like2Experience;

import java.util.List;

public interface Like2ExperienceService extends BaseService<Like2Experience> {

    List<Like2Experience> findAllByExperienceId(Long experienceId);

    Like2Experience findByExperienceIdAndUsername(Long experienceId, String username);

}
