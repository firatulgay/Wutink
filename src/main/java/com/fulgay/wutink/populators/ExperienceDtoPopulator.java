package com.fulgay.wutink.populators;

import com.fulgay.wutink.converter.UserDtoConverter;
import com.fulgay.wutink.domain.Category;
import com.fulgay.wutink.domain.Experience;
import com.fulgay.wutink.dtos.ExperienceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.stream.Collectors;

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
            target.setCategoryIdList(source.getCategory().stream().map(Category::getId).collect(Collectors.toList()));
            target.setCategoryNameList(source.getCategory().stream().map(Category::getName).collect(Collectors.toList()));


            String pattern = "dd MMMMM yyyy";
            target.setCreationTimeStr(new SimpleDateFormat(pattern, new Locale("tr", "TR")).format(source.getCreationTime()));
        }

    }
}
