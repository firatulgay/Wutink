package com.fulgay.bilenyum.service;

import com.fulgay.bilenyum.domain.Experience;

import java.util.List;

public interface ExperienceService extends BaseService<Experience> {
    List<Experience> findExperienceByHeader(String experienceName);

}
