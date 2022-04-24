package com.fulgay.wutink.populators;

import com.fulgay.wutink.domain.Comment;
import com.fulgay.wutink.dtos.CommentDto;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * @author Fırat ÜLGAY
 * @since 28.08.2021
 */

@Component
public class CommentDtoPopulator implements Populator<Comment, CommentDto> {

    @Override
    public void populate(Comment source, CommentDto target) {

        if (source != null){
            target.setDescription(source.getDescription());
            target.setExperienceId(source.getExperience().getId());
            target.setId(source.getId());
            target.setUsername(source.getUser().getUserName());

            String pattern = "dd MMMMM yyyy";
            target.setCreationTime(new SimpleDateFormat(pattern, new Locale("tr", "TR")).format(source.getCreationTime()));
        }
    }
}
