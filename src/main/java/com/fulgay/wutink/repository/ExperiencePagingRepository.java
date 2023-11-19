package com.fulgay.wutink.repository;

import com.fulgay.wutink.domain.Experience;
import com.fulgay.wutink.dtos.response.experience.ExperienceDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ExperiencePagingRepository extends PagingAndSortingRepository <Experience,Long> {

    @Query("SELECT NEW com.fulgay.wutink.dtos.response.experience.ExperienceDto(e.id, e.header, e.description, e.user.userName, e.creationTime, COUNT(DISTINCT l.id), COUNT(DISTINCT c.id)) " +
            "FROM Experience e " +
            "LEFT JOIN e.user u " +
            "LEFT JOIN Like2Experience l ON e.id = l.experience.id " +
            "LEFT JOIN Comment c ON e.id = c.experience.id " +
            "GROUP BY e.id")
    Page<ExperienceDto> findAllByPage(Pageable page);
}
