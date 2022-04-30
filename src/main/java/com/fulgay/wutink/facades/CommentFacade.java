package com.fulgay.wutink.facades;

import com.fulgay.wutink.converter.CommentConverter;
import com.fulgay.wutink.converter.CommentDtoConverter;
import com.fulgay.wutink.domain.Comment;
import com.fulgay.wutink.dtos.CommentDto;
import com.fulgay.wutink.dtos.PageSizeDto;
import com.fulgay.wutink.populators.CommentPopulator;
import com.fulgay.wutink.service.CommentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Fırat ÜLGAY
 * @since 28.08.2021
 */

@Component
public class CommentFacade {

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentDtoConverter commentDtoConverter;

    @Autowired
    private CommentConverter commentConverter;

    @Autowired
    private CommentPopulator commentPopulator;

    private static final Logger LOG = Logger.getLogger(ExperienceFacade.class);


    public List<CommentDto> findCommentsByExperienceId(Long id, int firstIndexOfPage,int offsetSize) {

        PageSizeDto pageSizeDto = new PageSizeDto();
        pageSizeDto.setFirstIndex(firstIndexOfPage);
        pageSizeDto.setOffsetSize(offsetSize);

        List<Comment> commentList = commentService.findCommentsByExperienceId(id,pageSizeDto);
        return commentDtoConverter.convertToList(commentList);

    }

    public List<CommentDto> findCommentsByUsername(String username) {

        List<Comment> commentList = commentService.findCommentsByUsername(username);
        return commentDtoConverter.convertToList(commentList);

    }

    public void saveComment(CommentDto commentDto) {
        Comment comment = commentConverter.convert(commentDto);
        Long id = commentService.save(comment);
        LOG.info("Comment saved successfully with id : " + id + " for experience : " + commentDto.getExperienceId());
    }

    public void deleteCommentById(Long id) {
        commentService.delete(commentService.findById(id));
        LOG.info("Comment deleted successfully with id : " + id);
    }

    public void updateComment(CommentDto commentDto) {
        Comment comment = commentService.findById(commentDto.getId());
        commentPopulator.populate(commentDto, comment);
        commentService.update(comment);

        LOG.info("Comment updated successfully with id : " + commentDto.getId());
    }

}
