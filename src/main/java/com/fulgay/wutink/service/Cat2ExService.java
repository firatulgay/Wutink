package com.fulgay.wutink.service;

import com.fulgay.wutink.domain.Cat2Ex;
import com.fulgay.wutink.domain.Category;
import com.fulgay.wutink.domain.Experience;
import com.fulgay.wutink.dtos.response.experience.ExperienceDto;

import java.util.List;

public interface Cat2ExService extends BaseService<Cat2Ex> {
    List<Experience> findExperienceByCategoryId(Long id);
    void deleteRelByExperience(Experience experience);
    List<Cat2Ex> findAllByExperienceAndCategory(Experience experience, Category category);
    List<Cat2Ex> findAllByExperience(Experience experience);
    List<ExperienceDto> findExperiencesByCategoryIdWithLikeAndCommentCount(Long id);
}
