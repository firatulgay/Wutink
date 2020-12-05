package com.fulgay.bilenyum.service;

import com.fulgay.bilenyum.dao.UserDaoImpl;
import com.fulgay.bilenyum.domain.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * UserService
 * 8/12/2020
 *
 * @author Fırat ÜLGAY
 * @since 8/12/2020
 */
public class UserService {
    @Autowired
    UserDaoImpl userDao;

    public User save(User user){
        User userSaved = (User)userDao.save(user);
        return userSaved;
    }

    public List<User> findAllUsers(){
        return userDao.findAll();
    }
}
