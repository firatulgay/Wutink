package com.fulgay.wutink.repository;

import com.fulgay.wutink.domain.Cat2Ex;
import com.fulgay.wutink.domain.Category;
import com.fulgay.wutink.domain.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Fırat ÜLGAY
 * @since 30.01.2021
 */

public interface Cat2ExRepository extends CrudRepository<Cat2Ex,Long> {

    @Query(value = "select ex from Cat2Ex cx" +
            " left join cx.category cat" +
            " left join cx.experience ex" +
            "  where cat.id = :id")
    List<Experience> findExperienceByCategoryId(@Param("id") Long id);

    void deleteRelByExperience(Experience experience);

    List<Cat2Ex> findByExperienceAndCategory(Experience experience, Category category);

    List<Cat2Ex> findAllByExperience(Experience experience);
}

