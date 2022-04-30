package com.fulgay.wutink.service;

import com.fulgay.wutink.domain.Comment;
import com.fulgay.wutink.dtos.PageSizeDto;

import java.util.List;

public interface CommentService extends BaseService<Comment> {

    List<Comment> findCommentsByExperienceId(Long id, PageSizeDto pageSizeDto);

    List<Comment> findCommentsByUsername(String username);
}
