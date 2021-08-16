package com.fulgay.wutink.dao.impl;

import com.fulgay.wutink.dao.Like2ExperienceDao;
import com.fulgay.wutink.domain.Like2Experience;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

/**
 * @author Fırat ÜLGAY
 * @since 1.08.2021
 */

@Repository
public class Like2ExperienceDaoImpl extends Like2ExperienceDao {

    @Override
    public List<Like2Experience> findAllByExperienceId(Long experienceId) {

        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Query query = session.createQuery("select like2Ex from Like2Experience like2Ex where like2Ex.experience.id = :id");
            query.setParameter("id", experienceId);

            return query.list();
        } catch (NoResultException e) {
            return null;
        } finally {
            transaction.commit();
        }
    }

    @Override
    public Like2Experience findByExperienceIdAndUsername(Long experienceId, String username) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Query query = session.createQuery("select like2Ex from Like2Experience like2Ex where like2Ex.experience.id = :experienceId and like2Ex.user.userName = :username");
            query.setParameter("username", username);
            query.setParameter("experienceId", experienceId);


            Like2Experience like2Experience = (Like2Experience) query.uniqueResult();
            return like2Experience;
        } catch (NoResultException e) {
            return null;
        } finally {
            transaction.commit();
        }
    }
}
