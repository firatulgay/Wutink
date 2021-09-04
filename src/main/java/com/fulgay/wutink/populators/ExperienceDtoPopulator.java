package com.fulgay.wutink.populators;

import com.fulgay.wutink.converter.UserDtoConverter;
import com.fulgay.wutink.domain.Experience;
import com.fulgay.wutink.dtos.ExperienceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Locale;

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

            String pattern = "dd MMMMM yyyy";
            target.setCreationTime(new SimpleDateFormat(pattern, new Locale("tr", "TR")).format(source.getCreationTime()));
        }

    }
}
