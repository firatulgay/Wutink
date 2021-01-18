package com.fulgay.bilenyum.service;

import com.fulgay.bilenyum.domain.User;

public interface UserService extends BaseService<User> {
    public User findUserByUserName(String userName);
    public User findUserByUserNameAndPassword(String userName, String password);
}
