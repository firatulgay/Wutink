package com.fulgay.wutink.converter;

import com.fulgay.wutink.domain.Comment;
import com.fulgay.wutink.dtos.CommentDto;
import com.fulgay.wutink.populators.CommentPopulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Fırat ÜLGAY
 * @since 28.08.2021
 */

@Component
public class CommentConverter extends Converter<CommentDto, Comment> {

    @Autowired
    private CommentPopulator commentPopulator;

    @Override
    public Comment convert(CommentDto source) {
        Comment target = new Comment();
        commentPopulator.populate(source,target);
        return target;
    }
}
