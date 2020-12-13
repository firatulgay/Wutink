package com.fulgay.bilenyum.utils.validators;

import com.fulgay.bilenyum.domain.User;
import com.fulgay.bilenyum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserValidator {

    @Autowired
    UserService userService;

    public boolean validateUserByUserName(String userName){
        User user = userService.findUserByUserName(userName);
        if (user != null){
            return false;
        }else {
            return true;
        }
    }
}
