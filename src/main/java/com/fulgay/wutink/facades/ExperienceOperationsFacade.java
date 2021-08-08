package com.fulgay.wutink.facades;

import com.fulgay.wutink.domain.Experience;
import com.fulgay.wutink.domain.Like2Experience;
import com.fulgay.wutink.domain.User;
import com.fulgay.wutink.service.ExperienceService;
import com.fulgay.wutink.service.Like2ExperienceService;
import com.fulgay.wutink.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Fırat ÜLGAY
 * @since 1.08.2021
 */

@Component
public class ExperienceOperationsFacade {

    @Autowired
    private Like2ExperienceService like2ExperienceService;

    @Autowired
    private ExperienceService experienceService;

    @Autowired
    private UserService userService;

    private static final Logger LOG = Logger.getLogger(ExperienceOperationsFacade.class);


    public void likeExprience(Long experienceId, String userName) {

        Experience experience = experienceService.findById(experienceId);
        User user = userService.findUserByUserName(userName);

        Like2Experience like2Experience = new Like2Experience();
        like2Experience.setExperience(experience);
        like2Experience.setUser(user);

        like2ExperienceService.save(like2Experience);

        LOG.info(userName + "  Liked an experience with id : " + experienceId);
    }

    public void unlikeExperience(Long experienceId, String userName) {

        Like2Experience like2Experience = like2ExperienceService.findByExperienceIdAndUsername(experienceId, userName);
        like2ExperienceService.delete(like2Experience);

        LOG.info(userName + "  unliked an experience with id : " + experienceId );
    }

    public void commentExprience(Long experienceId, Long userId, String comment) {
        //TODO Comment
    }

}
