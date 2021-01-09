package com.fulgay.bilenyum.converter;

import com.fulgay.bilenyum.domain.Experience;
import com.fulgay.bilenyum.dtos.ExperienceDto;
import com.fulgay.bilenyum.populators.ExperiencePopulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExperienceConverter extends Converter<ExperienceDto , Experience>{
    @Autowired
    ExperiencePopulator experiencePopulator;

    @Override
    public Experience convert(ExperienceDto source) {
        Experience target = new Experience();
        experiencePopulator.populate(source,target);
        return target;
    }
}
