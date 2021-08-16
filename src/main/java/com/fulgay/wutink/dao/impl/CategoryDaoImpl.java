package com.fulgay.wutink.dao.impl;

import com.fulgay.wutink.dao.CategoryDao;
import com.fulgay.wutink.domain.Category;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

@Repository
public class CategoryDaoImpl extends CategoryDao {

    @Override
    public Category findCategoryByName(String name) {

        Session session = getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Query query = session.createQuery("select category from Category category where name = :name");
            query.setParameter("name", name);

            return (Category) query.uniqueResult();
        } catch (NoResultException e) {
            return null;
        }finally {
            transaction.commit();
        }

    }
}
