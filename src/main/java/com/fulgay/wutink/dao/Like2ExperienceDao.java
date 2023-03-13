package com.fulgay.wutink.dao;

import com.fulgay.wutink.domain.Like2Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Fırat ÜLGAY
 * @since 1.08.2021
 */

public interface Like2ExperienceDao extends JpaRepository<Like2Experience, Long> {

    @Query(value = "select like2Ex from Like2Experience like2Ex where like2Ex.experience.id = :id")
    List<Like2Experience> findAllByExperienceId(Long experienceId);

    @Query(value = "select like2Ex from Like2Experience like2Ex where like2Ex.experience.id = :experienceId and like2Ex.user.userName = :username")
    Like2Experience findByExperienceIdAndUsername(Long experienceId, String username);
}
