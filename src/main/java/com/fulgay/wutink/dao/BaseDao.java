package com.fulgay.wutink.dao;

import com.fulgay.wutink.utils.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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

    private  Class<T> clazz;

    public BaseDao(Class<T> clazz) {
        this.clazz = clazz;
    }

    private SessionFactory sessionFactory;

    public Long save(T var) {
        Transaction transaction = getSession().beginTransaction();
        Long id = (Long) getSession().save(var);
        transaction.commit();

        return id;
    }

    public void delete(T var){
        Transaction transaction = getSession().beginTransaction();
        getSession().delete(var);
        transaction.commit();
    }

    public List<T> findAll(){

        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder criteriaBuilder =session.getCriteriaBuilder();
        CriteriaQuery<T> query = criteriaBuilder.createQuery(clazz);
        query.from(clazz);
        try {
            List<T> resultList = session.createQuery(query).getResultList();
            return resultList;
        } catch (NoResultException ex){
            return null;
        }finally {
            transaction.commit();
        }
    }

    public T findById(Long id){
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder criteriaBuilder =session.getCriteriaBuilder();
        CriteriaQuery<T> query = criteriaBuilder.createQuery(clazz);
        Root<T> root = query.from(clazz);
        query.select(root).where(criteriaBuilder.equal(root.get("id"),id));
        try {
            T singleResult = session.createQuery(query).getSingleResult();
            return singleResult;
        }catch (NoResultException ex){
            return null;
        }finally {
            transaction.commit();
        }
    }

    public Session getSession(){
         sessionFactory = HibernateUtil.getSessionFactory();
        if (!sessionFactory.isOpen()){
            return sessionFactory.openSession();
        }
        return sessionFactory.getCurrentSession();
    }
}
