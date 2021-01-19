package com.fulgay.wutink.utils.validators;

import com.fulgay.wutink.domain.User;
import com.fulgay.wutink.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserValidator {

    @Autowired
    UserServiceImpl userServiceImpl;

    public boolean validateUserByUserName(String userName)  {

        User user = userServiceImpl.findUserByUserName(userName);

        if (user != null){
            return false;
        }else {
            return true;
        }
    }
}
