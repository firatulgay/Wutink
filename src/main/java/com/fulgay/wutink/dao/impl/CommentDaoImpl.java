package com.fulgay.wutink.dao.impl;

import com.fulgay.wutink.dao.CommentDao;
import com.fulgay.wutink.domain.Comment;
import com.fulgay.wutink.dtos.PageSizeDto;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

/**
 * @author Fırat ÜLGAY
 * @since 28.08.2021
 */

@Repository
public class CommentDaoImpl extends CommentDao {


    @Override
    public List<Comment> findCommentsByExperienceId(Long id, PageSizeDto pageSizeDto) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Query query = session.createQuery("select com from Comment com" +
                    " left join com.experience ex" +
                    "  where ex.id = :id");
            query.setParameter("id", id);

            if (pageSizeDto != null){
                query.setFirstResult(pageSizeDto.getFirstIndex());
                query.setMaxResults(pageSizeDto.getOffsetSize());
            }

            return query.list();

        } catch (NoResultException e) {
            return null;
        } finally {
            transaction.commit();
        }
    }

    @Override
    public List<Comment> findCommentsByUsername(String username) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Query query = session.createQuery("select com from Comment com" +
                    " where com.username = :username");
            query.setParameter("username", username);
            return query.list();

        } catch (NoResultException e) {
            return null;
        } finally {
            transaction.commit();
        }
    }
}

