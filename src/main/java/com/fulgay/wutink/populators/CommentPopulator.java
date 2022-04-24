package com.fulgay.wutink.populators;

import com.fulgay.wutink.domain.Comment;
import com.fulgay.wutink.dtos.CommentDto;
import com.fulgay.wutink.service.ExperienceService;
import com.fulgay.wutink.service.SessionService;
import com.fulgay.wutink.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Fırat ÜLGAY
 * @since 28.08.2021
 */

@Component
public class CommentPopulator implements Populator<CommentDto,Comment> {

    @Autowired
    private ExperienceService experienceService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private UserService userService;

    @Override
    public void populate(CommentDto source, Comment target) {

        if (source != null){
            target.setUser(userService.findUserByUserName(sessionService.getSessionUserName()));
            target.setDescription(source.getDescription());
            target.setExperience(experienceService.findById(source.getExperienceId()));
            target.setCreationTime(new Date());
        }
    }
}
