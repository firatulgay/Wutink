package com.fulgay.wutink.facades;

import com.fulgay.wutink.commons.notificationMessages.EnumMessageType;
import com.fulgay.wutink.commons.notificationMessages.EnumSuccessMessage;
import com.fulgay.wutink.commons.notificationMessages.GlobalMessages;
import com.fulgay.wutink.domain.Comment;
import com.fulgay.wutink.domain.Experience;
import com.fulgay.wutink.domain.Like2Experience;
import com.fulgay.wutink.domain.User;
import com.fulgay.wutink.dtos.BaseDto;
import com.fulgay.wutink.service.*;
import org.apache.log4j.Logger;
import org.hibernate.JDBCException;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Date;

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

    @Autowired
    private SessionService sessionService;

    private static final Logger LOG = Logger.getLogger(ExperienceOperationsFacade.class);


    public void likeExprience(Long experienceId) {

        Experience experience = experienceService.findById(experienceId);
        User user = userService.findUserByUserName(sessionService.getSessionUserName());

        Like2Experience like2Experience = new Like2Experience();
        like2Experience.setExperience(experience);
        like2Experience.setUser(user);

        like2ExperienceService.save(like2Experience);

        LOG.info(user.getUserName() + "  Liked an experience with id : " + experienceId);
    }

    public void unlikeExperience(Long experienceId) {

        String userName = sessionService.getSessionUserName();

        Like2Experience like2Experience = like2ExperienceService.findByExperienceIdAndUsername(experienceId, userName);
        like2ExperienceService.delete(like2Experience);

        LOG.info(userName +"  unliked an experience with id : " + experienceId );
    }

}
