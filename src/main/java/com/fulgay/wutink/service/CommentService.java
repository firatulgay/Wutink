package com.fulgay.wutink.service;

import com.fulgay.wutink.domain.Comment;

import java.util.List;

public interface CommentService extends BaseService<Comment> {

    List<Comment> findCommentsByExperienceId(Long id);

    List<Comment> findCommentsByUsername(String username);
}
