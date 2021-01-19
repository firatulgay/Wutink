package com.fulgay.wutink.service;

import com.fulgay.wutink.domain.User;

public interface UserService extends BaseService<User> {
    public User findUserByUserName(String userName);
    public User findUserByUserNameAndPassword(String userName, String password);
}
