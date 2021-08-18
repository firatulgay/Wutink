package com.fulgay.wutink.dao;

import com.fulgay.wutink.domain.Cat2Ex;
import com.fulgay.wutink.domain.Category;
import com.fulgay.wutink.domain.Experience;

import java.util.List;

/**
 * @author Fırat ÜLGAY
 * @since 30.01.2021
 */

public abstract class Cat2ExDao extends BaseDao<Cat2Ex> {

    public Cat2ExDao() {
        super(Cat2Ex.class);
    }

    public abstract List<Experience> findExperienceByCategoryId(Long id);

    public abstract void deleteRelByExperience(Experience experience);

    public abstract List<Cat2Ex> findByExperienceAndCategory(Experience experience, Category category);

    public abstract List<Cat2Ex> findAllByExperience(Experience experience);
}

