package com.fulgay.wutink.dao.impl;

import com.fulgay.wutink.dao.Cat2ExDao;
import com.fulgay.wutink.domain.Cat2Ex;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class Cat2ExDaoImpl extends Cat2ExDao {

    @Override
    public List<Cat2Ex> findCat2ExRelByCategoryId(Long id){
        Session session = getSession();
        Query query = session.createQuery("select cx from Cat2Ex cx left join cx.category cat  where cat.id = :id");
        query.setParameter("id",id);

        return query.list();
    }

}
