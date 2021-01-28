package com.fulgay.wutink.dao.impl;

import com.fulgay.wutink.dao.BaseDao;
import com.fulgay.wutink.domain.User;
import com.fulgay.wutink.enums.EnumUserType;

import java.util.List;

/**
 * BaseUserDao
 * 8/12/2020
 *
 * @author Fırat ÜLGAY
 * @since 8/12/2020
 */
public abstract class UserDao extends BaseDao<User> {

    public UserDao() {
        super(User.class);
    }

    public abstract User findUserByUserName(String userName);

    public abstract List<User> findAllByUserType(EnumUserType userType);

    public abstract User findUserByUserNameAndPassword(String userName, String password);
}
