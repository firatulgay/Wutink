package com.fulgay.wutink.converter;

import com.fulgay.wutink.domain.Experience;
import com.fulgay.wutink.dtos.response.experience.ExperienceDto;
import com.fulgay.wutink.populators.ExperiencePopulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExperienceConverter extends Converter<ExperienceDto , Experience>{
    @Autowired
    private ExperiencePopulator experiencePopulator;

    @Override
    public Experience convert(ExperienceDto source) {
        Experience target = new Experience();
        experiencePopulator.populate(source,target);
        return target;
    }
}
