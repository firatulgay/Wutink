package com.fulgay.wutink.converter;

import com.fulgay.wutink.domain.Experience;
import com.fulgay.wutink.dtos.response.experience.ExperienceDto;
import com.fulgay.wutink.populators.ExperienceDtoPopulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExperienceDtoConverter extends Converter<Experience, ExperienceDto> {

    @Autowired
    private ExperienceDtoPopulator experienceDtoPopulator;

    @Override
    public ExperienceDto convert(Experience source) {
        ExperienceDto target = new ExperienceDto();
        experienceDtoPopulator.populate(source,target);
        return target;
    }
}
