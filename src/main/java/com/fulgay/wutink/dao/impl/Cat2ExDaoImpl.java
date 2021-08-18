package com.fulgay.wutink.dao.impl;

import com.fulgay.wutink.controllers.LoginController;
import com.fulgay.wutink.dao.Cat2ExDao;
import com.fulgay.wutink.domain.Cat2Ex;
import com.fulgay.wutink.domain.Category;
import com.fulgay.wutink.domain.Experience;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class Cat2ExDaoImpl extends Cat2ExDao {

    private static final Logger LOG = Logger.getLogger(LoginController.class);


    @Override
    public List<Experience> findExperienceByCategoryId(Long id) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Query query = session.createQuery("select ex from Cat2Ex cx" +
                    " left join cx.category cat" +
                    " left join cx.experience ex" +
                    "  where cat.id = :id");
            query.setParameter("id", id);
            return query.list();

        } catch (NoResultException e) {
            return null;
        } finally {
            transaction.commit();
        }
    }

    @Override
    public void deleteRelByExperience(Experience experience) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Query query = session.createQuery("delete from Cat2Ex cx where cx.experience = :experience");
            query.setParameter("experience", experience);
            query.executeUpdate();
        } catch (NoResultException e) {
            return;
        } finally {
            transaction.commit();
        }
    }

    @Override
    public List<Cat2Ex> findByExperienceAndCategory(Experience experience, Category category) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Query query = session.createQuery("select cx from Cat2Ex cx where cx.experience = :experience and cx.category = :category");
            query.setParameter("experience", experience);
            query.setParameter("category", category);

            return query.list();

        } catch (NoResultException e) {
            return null;
        } finally {
            transaction.commit();
        }
    }

    @Override
    public List<Cat2Ex> findAllByExperience(Experience experience) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Query query = session.createQuery("select cx from Cat2Ex cx where cx.experience = :experience");
            query.setParameter("experience", experience);

            return query.list();

        } catch (NoResultException e) {
            return null;
        } finally {
            transaction.commit();
        }
    }
}
