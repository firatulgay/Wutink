package com.fulgay.wutink.dao.impl;

import com.fulgay.wutink.dao.ExperienceDao;
import com.fulgay.wutink.domain.Experience;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExperienceDaoImpl extends ExperienceDao {

    @Override
    public List<Experience> findExperienceByHeader(String header) {

        /**
         *       CRITERIA VERSION OF LIKE QUERY (GIVES BUILD EROR!!)
         *
         *       Session session = getSession();
         *
         *       CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
         *       CriteriaQuery<Experience> query = criteriaBuilder.createQuery(Experience.class);
         *       Root<Experience> root = query.from(Experience.class);
         *       query.select(root).where(criteriaBuilder.like(root.get("header"), "%" + header + "%"));
         */

        Session session = getSession();
        Query query = session.createQuery("select ex from Experience ex where ex.header like :parameter");
        query.setParameter("parameter", "%"+header+"%");

        return query.list();
    }

    @Override
    public List<Experience> findAllExperiencesByCategoryId(Long id) {

        Session session = getSession();
        Query query = session.createQuery("select ex from Experience ex left join ex.category cat  where cat.id = :id");
        query.setParameter("id", id);

        return query.list();
    }

    @Override
    public List<Experience> findAllExperiencesByUserName(String userName) {

        Session session = getSession();
        Query query = session.createQuery("select ex from Experience ex left join ex.user user  where user.userName = :userName");
        query.setParameter("userName", userName);

        return query.list();
    }
}
