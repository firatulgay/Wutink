package com.fulgay.bilenyum.converter;

import com.fulgay.bilenyum.domain.Experience;
import com.fulgay.bilenyum.dtos.ExperienceDto;
import com.fulgay.bilenyum.populators.ExperienceDtoPopulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExperienceDtoConverter extends Converter<Experience, ExperienceDto> {

    @Autowired
    ExperienceDtoPopulator experienceDtoPopulator;

    @Override
    public ExperienceDto convert(Experience source) {
        ExperienceDto target = new ExperienceDto();
        experienceDtoPopulator.populate(source,target);
        return target;
    }
}
