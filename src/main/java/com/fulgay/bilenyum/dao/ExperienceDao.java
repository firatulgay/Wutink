package com.fulgay.bilenyum.dao;

import com.fulgay.bilenyum.domain.Experience;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class ExperienceDao extends BaseDao<Experience> {

    public ExperienceDao() {
        super(Experience.class);
    }

    public List<Experience> findExperienceByHeader(String header) {

        Session session = getSession();

        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Experience> query = criteriaBuilder.createQuery(Experience.class);
        Root<Experience> root = query.from(Experience.class);
        query.select(root)
                .where(criteriaBuilder
                        .like(root.get("header"), "%" + header + "%"));

        List<Experience> list = session.createQuery(query).list();
        return list;
    }

    public List<Experience> findAllExperiencesByCategoryId(Long id) {

        Session session = getSession();
        Query query = session.createQuery("select ex from Experience ex left join ex.category cat  where cat.id = :id");
        query.setParameter("id", id);

        return query.list();
    }

    public List<Experience> findAllExperiencesByUserName(String userName) {

        Session session = getSession();
        Query query = session.createQuery("select ex from Experience ex left join ex.user user  where user.userName = :userName");
        query.setParameter("userName", userName);

        return query.list();
    }
}
