package com.fulgay.wutink.service;

import com.fulgay.wutink.domain.Experience;

import java.util.List;

public interface ExperienceService extends BaseService<Experience> {
    List<Experience> findExperienceByHeader(String experienceName);

}
