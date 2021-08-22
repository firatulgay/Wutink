package com.fulgay.wutink.populators;

import com.fulgay.wutink.converter.UserDtoConverter;
import com.fulgay.wutink.domain.Experience;
import com.fulgay.wutink.dtos.ExperienceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;

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
            target.setUserName(source.getUser().getUserName());

            LocalDate localDate = source.getCreationTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            target.setCreationTime(localDate.toString());
        }

    }
}
