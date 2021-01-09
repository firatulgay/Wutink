package com.fulgay.bilenyum.populators;

import com.fulgay.bilenyum.domain.Experience;
import com.fulgay.bilenyum.dtos.ExperienceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExperienceDtoPopulator implements Populator<Experience,ExperienceDto> {

    private ExperienceDto experienceDto;

    @Autowired
    UserDtoPopulator userDtoPopulator;

    @Override
    public ExperienceDto populate(Experience source) {
        experienceDto = new ExperienceDto();

        if (source != null) {
            experienceDto.setDescription(source.getDescription());
            experienceDto.setHeader(source.getHeader());
            experienceDto.setId(source.getId());
            experienceDto.setUserDto(userDtoPopulator.populate(source.getUser()));
        }

        return experienceDto;
    }
}
