package com.fulgay.wutink.populators;

import com.fulgay.wutink.domain.Experience;
import com.fulgay.wutink.dtos.ExperienceDto;
import com.fulgay.wutink.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExperiencePopulator implements Populator<ExperienceDto, Experience> {

    @Autowired
    UserServiceImpl userServiceImpl;

    @Override
    public void populate(ExperienceDto source, Experience target) {

        if (source != null) {
            target.setHeader(source.getHeader());
            target.setUser(userServiceImpl.findUserByUserName(source.getUserName()));
            target.setDescription(source.getDescription());
        }
    }
}
