package com.fulgay.wutink.repository;

import com.fulgay.wutink.domain.Cat2Ex;
import com.fulgay.wutink.domain.Category;
import com.fulgay.wutink.domain.Experience;
import com.fulgay.wutink.dtos.response.experience.ExperienceDto;
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


    @Query("SELECT NEW com.fulgay.wutink.dtos.response.experience.ExperienceDto(e.id, e.header, e.description, e.user.userName, e.creationTime, COUNT(DISTINCT l.id), COUNT(DISTINCT c.id)) " +
            "FROM Experience e " +
            "LEFT JOIN e.user u " +
            "LEFT JOIN Like2Experience l ON e.id = l.experience.id " +
            "LEFT JOIN Comment c ON e.id = c.experience.id " +
            "WHERE e.id IN (" +
            "SELECT ce.experience.id FROM Cat2Ex ce WHERE ce.category.id = :id" +
            ") " +
            "GROUP BY e.id")
    List<ExperienceDto> findExperiencesByCategoryIdWithLikeAndCommentCount(@Param("id") Long id);

    void deleteRelByExperience(Experience experience);

    List<Cat2Ex> findByExperienceAndCategory(Experience experience, Category category);

    List<Cat2Ex> findAllByExperience(Experience experience);
}

