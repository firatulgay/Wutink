package com.fulgay.bilenyum.dao;

import com.fulgay.bilenyum.domain.User;
import com.fulgay.bilenyum.enums.EnumUserType;
import org.hibernate.Session;
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
public class UserDaoImpl extends BaseUserDao {

    private User user = null;

    public UserDaoImpl() {
        super();
    }

    @Override
    public User findUserByUserName(String userName) {
        Session session = getSession();
        CriteriaBuilder criteriaBuilder =session.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root).where(criteriaBuilder.equal(root.get("userName"),userName));

        try {
            user = session.createQuery(query).getSingleResult();
        }catch (NoResultException e){ }

        return user;
    }

    @Override
    public List<User> findAllByUserType(EnumUserType userType) {
        return null;
    }

}
