package com.fulgay.wutink.dao;

import com.fulgay.wutink.domain.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Fırat ÜLGAY
 * @since 29.01.2021
 */

public interface ExperienceDao extends JpaRepository<Experience,Long> {

    @Query(value = "select ex from Experience ex where ex.header like :header")
    List<Experience> findExperienceByHeader(@Param("header") String header);

    @Query(value = "select ex from Experience ex JOIN FETCH ex.user user  where user.userName = :userName")
    List<Experience> findAllExperienceByUserName(@Param("userName") String userName);


    }
