package com.fulgay.bilenyum.populators;

import com.fulgay.bilenyum.domain.Experience;
import com.fulgay.bilenyum.dtos.ExperienceDto;
import com.fulgay.bilenyum.service.impl.UserServiceImpl;
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
            target.setUser(userServiceImpl.findUserByUserName(source.getUserDto().getUserName()));
            target.setDescription(source.getDescription());
        }
    }
}
