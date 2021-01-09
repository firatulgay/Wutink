package com.fulgay.bilenyum.populators;

import com.fulgay.bilenyum.domain.Experience;
import com.fulgay.bilenyum.dtos.ExperienceDto;
import com.fulgay.bilenyum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExperiencePopulator implements Populator<ExperienceDto,Experience> {

    private Experience experience;
    private ExperienceDto experienceDto;

    @Autowired
    UserService userService;

    @Override
    public Experience populate(ExperienceDto source) {
        experience = new Experience();

        if (source != null) {
            experience.setHeader(source.getHeader());
            experience.setUser(userService.findUserByUserName(source.getUserDto().getUserName()));
            experience.setDescription(source.getDescription());
        }
        return experience;

    }
}
