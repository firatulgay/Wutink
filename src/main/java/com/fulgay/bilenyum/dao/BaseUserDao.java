package com.fulgay.bilenyum.dao;

import com.fulgay.bilenyum.domain.User;
import com.fulgay.bilenyum.enums.EnumUserType;

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

}
