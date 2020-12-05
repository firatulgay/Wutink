package com.fulgay.bilenyum.dao;

import com.fulgay.bilenyum.domain.User;
import com.fulgay.bilenyum.enums.EnumUserType;
import com.fulgay.bilenyum.utils.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * UserDaoImpl
 * 8/12/2020
 *
 * @author Fırat ÜLGAY
 * @since 8/12/2020
 */
public class UserDaoImpl extends BaseUserDao {

    public UserDaoImpl() {
        super();
    }

    @Override
    public User findUserByUserName(String userName) {
        return null;
    }

    @Override
    public List<User> findAllByUserType(EnumUserType userType) {
        return null;
    }

}
