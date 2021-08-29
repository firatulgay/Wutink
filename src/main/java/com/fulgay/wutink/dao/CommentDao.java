package com.fulgay.wutink.dao;

import com.fulgay.wutink.domain.Comment;

import java.util.List;

/**
 * @author Fırat ÜLGAY
 * @since 28.08.2021
 */

public abstract class CommentDao extends BaseDao<Comment> {

    public CommentDao() {
        super(Comment.class);
    }

    public abstract List<Comment> findCommentsByExperienceId(Long id);
    public abstract List<Comment> findCommentsByUsername(String username);
}
