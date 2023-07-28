package com.fulgay.wutink.service.impl;

import com.fulgay.wutink.repository.UserRepository;
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
    private UserRepository userRepository;

    public Long save(User user) {
        return userRepository.save(user).getId();
    }

    @Override
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User obj) {

    }

    @Override
    public User findUserByUserName(String userName) {
        User user = userRepository.findUserByUserName(userName);
        return user;
    }

    @Override
    public User findUserByUserNameAndPassword(String userName, String password) {
        User user = userRepository.findUserByUserNameAndPassword(userName, password);
        return user;
    }
}
