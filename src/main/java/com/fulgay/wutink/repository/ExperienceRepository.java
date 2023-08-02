package com.fulgay.wutink.repository;

import com.fulgay.wutink.domain.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Fırat ÜLGAY
 * @since 29.01.2021
 */

public interface ExperienceRepository extends CrudRepository<Experience,Long> {

    @Query(value = "select ex from Experience ex where ex.header like :header")
    List<Experience> findExperienceByHeader(@Param("header") String header);

    @Query(value = "select ex from Experience ex JOIN FETCH ex.user user  where user.userName = :userName")
    List<Experience> findAllExperienceByUserName(@Param("userName") String userName);

    List<Experience> findByHeaderOrDescriptionContainingIgnoreCase(String searchTextHeader,String searchTextDesc);

}
