package com.fulgay.wutink.dao;

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
public abstract class BaseUserDao extends BaseDao<User> {

    public BaseUserDao() {
        super(User.class);
    }

    abstract User findUserByUserName(String userName);

    abstract List<User> findAllByUserType(EnumUserType userType);

    public abstract User findUserByUserNameAndPassword(String userName, String password);
}
