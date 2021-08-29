package com.fulgay.wutink.populators;

import com.fulgay.wutink.domain.Comment;
import com.fulgay.wutink.dtos.CommentDto;
import com.fulgay.wutink.service.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Fırat ÜLGAY
 * @since 28.08.2021
 */

@Component
public class CommentPopulator implements Populator<CommentDto,Comment> {

    @Autowired
    private ExperienceService experienceService;

    @Override
    public void populate(CommentDto source, Comment target) {

        if (source != null){
            target.setUsername(source.getUsername());
            target.setDescription(source.getDescription());
            target.setExperience(experienceService.findById(source.getExperienceId()));
        }
    }
}
