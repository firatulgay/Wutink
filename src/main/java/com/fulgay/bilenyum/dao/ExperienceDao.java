package com.fulgay.bilenyum.dao;

import com.fulgay.bilenyum.domain.Experience;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExperienceDao extends BaseDao<Experience> {

    public ExperienceDao() {
        super(Experience.class);
    }

    public Experience findExperienceByHeader(String header){

        Session session = getSession();
        Query query = session.createQuery("select experience from Experience experience where header = :header");
        query.setParameter("header",header);

        return (Experience) query.uniqueResult();
    }

    public List<Experience> findAllExperiencesByCategoryId(Long id){

        Session session = getSession();
        Query query = session.createQuery("select ex from Experience ex left join ex.category cat  where cat.id = :id");
        query.setParameter("id",id);

        return query.list();
    }

    public List<Experience> findAllExperiencesByUserName(String userName){

        Session session = getSession();
        Query query = session.createQuery("select ex from Experience ex left join ex.user user  where user.userName = :userName");
        query.setParameter("userName",userName);

        return query.list();
    }
}
