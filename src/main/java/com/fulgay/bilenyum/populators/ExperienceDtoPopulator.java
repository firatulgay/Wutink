package com.fulgay.bilenyum.populators;

import com.fulgay.bilenyum.converter.UserDtoConverter;
import com.fulgay.bilenyum.domain.Experience;
import com.fulgay.bilenyum.dtos.ExperienceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExperienceDtoPopulator implements Populator<Experience,ExperienceDto> {

    @Autowired
    UserDtoConverter userDtoConverter;

    @Override
    public void populate(Experience source, ExperienceDto target) {
        if (source != null) {
            target.setDescription(source.getDescription());
            target.setHeader(source.getHeader());
            target.setId(source.getId());
            target.setUserDto(userDtoConverter.convert(source.getUser()));
        }

    }
}
