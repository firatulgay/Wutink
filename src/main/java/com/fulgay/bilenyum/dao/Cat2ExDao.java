package com.fulgay.bilenyum.dao;

import com.fulgay.bilenyum.domain.Cat2Ex;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class Cat2ExDao extends BaseDao<Cat2Ex>{
    public Cat2ExDao() {
        super(Cat2Ex.class);
    }

    public List<Cat2Ex> findCat2ExRelByCategoryId(Long id){
        Session session = getSession();
        Query query = session.createQuery("select cx from Cat2Ex cx left join cx.category cat  where cat.id = :id");
        query.setParameter("id",id);

        return query.list();
    }

}
