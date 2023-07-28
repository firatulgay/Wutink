package com.fulgay.wutink.repository;

import com.fulgay.wutink.domain.Like2Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Fırat ÜLGAY
 * @since 1.08.2021
 */

public interface Like2ExperienceRepository extends CrudRepository<Like2Experience, Long> {

    @Query(value = "select like2Ex from Like2Experience like2Ex where like2Ex.experience.id = :experienceId")
    List<Like2Experience> findAllByExperienceId(@Param("experienceId") Long experienceId);

    @Query(value = "select like2Ex from Like2Experience like2Ex where like2Ex.experience.id = :experienceId and like2Ex.user.userName = :username")
    Like2Experience findByExperienceIdAndUsername(@Param("experienceId") Long experienceId,@Param("username") String username);
}
