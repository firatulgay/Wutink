package com.fulgay.bilenyum.populators;

import com.fulgay.bilenyum.domain.Experience;
import com.fulgay.bilenyum.dtos.ExperienceDto;
import com.fulgay.bilenyum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExperiencePopulator implements Populator<ExperienceDto, Experience> {

    @Autowired
    UserService userService;

    @Override
    public void populate(ExperienceDto source, Experience target) {

        if (source != null) {
            target.setHeader(source.getHeader());
            target.setUser(userService.findUserByUserName(source.getUserDto().getUserName()));
            target.setDescription(source.getDescription());
        }
    }
}
