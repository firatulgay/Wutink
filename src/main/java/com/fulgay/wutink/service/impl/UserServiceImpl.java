package com.fulgay.wutink.service.impl;

import com.fulgay.wutink.dao.impl.UserDao;
import com.fulgay.wutink.domain.User;
import com.fulgay.wutink.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * UserService
 * 8/12/2020
 *
 * @author Fırat ÜLGAY
 * @since 8/12/2020
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    public Long save(User user) {
        return userDao.save(user);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public void update(User user) {

    }

    @Override
    public User findUserByUserName(String userName) {
        User user = userDao.findUserByUserName(userName);
        return user;
    }

    @Override
    public User findUserByUserNameAndPassword(String userName, String password) {
        User user = userDao.findUserByUserNameAndPassword(userName, password);
        return user;
    }
}
