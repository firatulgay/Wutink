package com.fulgay.wutink.service;

import com.fulgay.wutink.domain.Experience;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ExperienceService extends BaseService<Experience> {
    List<Experience> findExperienceByHeader(String experienceName);

    List<Experience> findAllExperiencesByUsername(String username);
    Page<Experience> findAllExperiencesByPage(Pageable pageable);
}
