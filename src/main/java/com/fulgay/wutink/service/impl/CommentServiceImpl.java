package com.fulgay.wutink.service.impl;

import com.fulgay.wutink.dao.CommentDao;
import com.fulgay.wutink.domain.Comment;
import com.fulgay.wutink.dtos.PageSizeDto;
import com.fulgay.wutink.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Fırat ÜLGAY
 * @since 28.08.2021
 */

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Override
    public List<Comment> findCommentsByExperienceId(Long id, PageSizeDto pageSizeDto) {
        return commentDao.findCommentsByExperienceId(id,pageSizeDto);
    }

    @Override
    public List<Comment> findCommentsByUsername(String username) {
        return commentDao.findCommentsByUsername(username);
    }

    @Override
    public Long save(Comment obj) {
        return commentDao.save(obj);
    }

    @Override
    public List<Comment> findAll() {
        return commentDao.findAll();
    }

    @Override
    public Comment findById(Long id) {
        return commentDao.findById(id);
    }

    @Override
    public void update(Comment obj) {
        commentDao.update(obj);
    }

    @Override
    public void delete(Comment obj) {
        commentDao.delete(obj);
    }
}
