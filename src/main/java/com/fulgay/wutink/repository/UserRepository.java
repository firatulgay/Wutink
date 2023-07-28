package com.fulgay.wutink.repository;

import com.fulgay.wutink.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * BaseUserDao
 * 8/12/2020
 *
 * @author Fırat ÜLGAY
 * @since 8/12/2020
 */
public interface UserRepository extends CrudRepository<User,Long> {

    User findUserByUserName(String userName);
    User findUserByUserNameAndPassword(String userName, String password);
}
