package com.fulgay.wutink.populators;

import com.fulgay.wutink.domain.Experience;
import com.fulgay.wutink.dtos.response.experience.ExperienceDto;
import com.fulgay.wutink.service.SessionService;
import com.fulgay.wutink.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExperiencePopulator implements Populator<ExperienceDto, Experience> {

    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private SessionService sessionService;

    @Override
    public void populate(ExperienceDto source, Experience target) {

        if (source != null) {
            target.setHeader(source.getHeader());
            target.setUser(userServiceImpl.findUserByUserName(sessionService.getSessionUserName()));
            target.setDescription(source.getDescription());
        }
    }
}
