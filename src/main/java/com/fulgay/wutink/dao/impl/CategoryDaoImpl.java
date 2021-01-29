package com.fulgay.wutink.dao.impl;

import com.fulgay.wutink.dao.CategoryDao;
import com.fulgay.wutink.domain.Category;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDaoImpl extends CategoryDao {

    @Override
    public Category findCategoryByName(String name){

        Session session = getSession();
        Query query = session.createQuery("select category from Category category where name = :name");
        query.setParameter("name",name);

        return (Category) query.uniqueResult();
    }
}
