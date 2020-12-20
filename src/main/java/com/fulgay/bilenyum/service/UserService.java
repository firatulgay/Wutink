package com.fulgay.bilenyum.service;

import com.fulgay.bilenyum.dao.UserDaoImpl;
import com.fulgay.bilenyum.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * UserService
 * 8/12/2020
 *
 * @author Fırat ÜLGAY
 * @since 8/12/2020
 */
@Service
public class UserService {
    @Autowired
    UserDaoImpl userDao;

    @Transactional(rollbackFor = Exception.class)
    public Long save(User user) {
        try {
            Long userId = userDao.save(user);
            return userId;
        }catch (UnexpectedRollbackException e){
            return null;
        }
    }

    public List<User> findAllUsers() {
        return userDao.findAll();
    }

    public User findUserById(Long id) {
        return userDao.findById(id);
    }

    public User findUserByUserName(String userName) {
        try {
            User user = userDao.findUserByUserName(userName);
            return user;
        } catch (EmptyResultDataAccessException e) {
                return null;
        }
    }
}
