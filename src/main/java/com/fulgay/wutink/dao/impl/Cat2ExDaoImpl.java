package com.fulgay.wutink.dao.impl;

import com.fulgay.wutink.dao.Cat2ExDao;
import com.fulgay.wutink.domain.Experience;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class Cat2ExDaoImpl extends Cat2ExDao {

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

}
