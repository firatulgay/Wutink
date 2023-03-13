package com.fulgay.wutink.dao;

import com.fulgay.wutink.domain.User;
import com.fulgay.wutink.enums.EnumUserType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * BaseUserDao
 * 8/12/2020
 *
 * @author Fırat ÜLGAY
 * @since 8/12/2020
 */
public interface UserDao extends JpaRepository<User,Long> {

    User findUserByUserName(String userName);
    User findUserByUserNameAndPassword(String userName, String password);
}
