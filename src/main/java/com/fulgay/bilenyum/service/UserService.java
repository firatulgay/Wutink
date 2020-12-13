package com.fulgay.bilenyum.service;

import com.fulgay.bilenyum.dao.UserDaoImpl;
import com.fulgay.bilenyum.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * UserService
 * 8/12/2020
 *
 * @author Fırat ÜLGAY
 * @since 8/12/2020
 */
@Service
@Transactional
public class UserService {
    @Autowired
    UserDaoImpl userDao;

    public Long save(User user){
        Long userId = userDao.save(user);
        return userId;
    }

    public List<User> findAllUsers(){
        return userDao.findAll();
    }

    public User findUserById(Long id) {
        return userDao.findById(id);
    }

    public User findUserByUserName(String userName) {
        return userDao.findUserByUserName(userName);
    }
}
