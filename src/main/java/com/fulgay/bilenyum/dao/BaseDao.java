package com.fulgay.bilenyum.dao;

import com.fulgay.bilenyum.domain.User;
import com.fulgay.bilenyum.utils.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * BaseDao
 * 8/12/2020
 *
 * @author Fırat ÜLGAY
 * @since 8/12/2020
 */
@Transactional(rollbackFor = Exception.class)
public abstract class BaseDao<T> {

    private final Class<T> clazz;

    public BaseDao(Class<T> clazz) {
        this.clazz = clazz;
    }

    private SessionFactory sessionFactory;

    public T save(T var){
        return (T)getSession().merge(var);
    }

    public void delete(T var){
     getSession().delete(var);
    }

    public List<T> findAll(){

        Session session = getSession();
        CriteriaBuilder criteriaBuilder =session.getCriteriaBuilder();
        CriteriaQuery<T> query = criteriaBuilder.createQuery(clazz);
        query.from(clazz);
        return session.createQuery(query).getResultList();
    }

    public T findById(Long var){
        return null;
    }

    Session getSession(){
        sessionFactory = HibernateUtil.getSessionFactory();
        return sessionFactory.openSession();
    }
}
