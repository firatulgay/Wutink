package com.fulgay.wutink.utils.validators;

import com.fulgay.wutink.commons.notificationMessages.EnumErrorMessage;
import com.fulgay.wutink.commons.wutinkExceptions.WutinkUserSaveException;
import com.fulgay.wutink.domain.User;
import com.fulgay.wutink.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserValidator {

    @Autowired
    private UserServiceImpl userServiceImpl;

    public void validateUserByUserName(String userName)  {
        User user = userServiceImpl.findUserByUserName(userName);

        if (user != null)
            throw new WutinkUserSaveException(EnumErrorMessage.USERNAME_ALREADY_EXIST.getValue());
    }
}
