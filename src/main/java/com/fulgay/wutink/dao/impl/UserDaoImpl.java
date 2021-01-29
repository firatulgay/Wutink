package com.fulgay.wutink.dao.impl;

import com.fulgay.wutink.dao.UserDao;
import com.fulgay.wutink.domain.User;
import com.fulgay.wutink.enums.EnumUserType;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * UserDaoImpl
 * 8/12/2020
 *
 * @author Fırat ÜLGAY
 * @since 8/12/2020
 */
@Repository
public class UserDaoImpl extends UserDao {


    public UserDaoImpl() {
        super();
    }

    @Override
    public User findUserByUserName(String userName) {
        Session session = getSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root).where(criteriaBuilder.equal(root.get("userName"), userName));

        try {
            return session.createQuery(query).getSingleResult();
        }catch (NoResultException ex){
            return null;
        }    }

    @Override
    public List<User> findAllByUserType(EnumUserType userType) {
        return null;
    }

    @Override
    public User findUserByUserNameAndPassword(String userName, String password) {

        Session session = getSession();
        Query query = session.createQuery("select user from User user where userName = :username and password = :password");
        query.setParameter("username",userName);
        query.setParameter("password",password);

        return (User) query.uniqueResult();
    }

}
