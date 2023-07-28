package com.fulgay.wutink.service.impl;

import com.fulgay.wutink.repository.CommentRepository;
import com.fulgay.wutink.domain.Comment;
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
    private CommentRepository commentRepository;

    @Override
    public List<Comment> findCommentsByExperienceId(Long id) {
        return commentRepository.findCommentsByExperienceId(id);
    }

    @Override
    public List<Comment> findCommentsByUsername(String username) {
        return commentRepository.findCommentsByUsername(username);
    }

    @Override
    public Long save(Comment obj) {
        return commentRepository.save(obj).getId();
    }

    @Override
    public List<Comment> findAll() {
        return (List<Comment>) commentRepository.findAll();
    }

    @Override
    public Comment findById(Long id) {
        return commentRepository.findById(id).get();
    }

    @Override
    public void update(Comment obj) {
        commentRepository.save(obj);
    }

    @Override
    public void delete(Comment obj) {
        commentRepository.delete(obj);
    }
}
