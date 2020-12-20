package com.fulgay.bilenyum.dao;

import com.fulgay.bilenyum.domain.Category;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDao extends BaseDao<Category>{
    public CategoryDao() {
        super(Category.class);
    }

    public Category findCategoryByName(String name){

        Session session = getSession();
        Query query = session.createQuery("select category from Category category where name = :name");
        query.setParameter("name",name);

        return (Category) query.uniqueResult();
    }
}
