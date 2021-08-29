package com.fulgay.wutink.converter;

import com.fulgay.wutink.domain.Comment;
import com.fulgay.wutink.dtos.CommentDto;
import com.fulgay.wutink.populators.CommentDtoPopulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Fırat ÜLGAY
 * @since 28.08.2021
 */

@Component
public class CommentDtoConverter extends Converter<Comment, CommentDto>{

    @Autowired
    private CommentDtoPopulator commentDtoPopulator;

    @Override
    public CommentDto convert(Comment source) {
        CommentDto target = new CommentDto();
        commentDtoPopulator.populate(source,target);
        return target;
    }
}
