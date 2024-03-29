package com.fulgay.wutink.facades;

import com.fulgay.wutink.commons.notificationMessages.EnumErrorMessage;
import com.fulgay.wutink.commons.notificationMessages.EnumMessageType;
import com.fulgay.wutink.commons.notificationMessages.EnumSuccessMessage;
import com.fulgay.wutink.commons.notificationMessages.GlobalMessages;
import com.fulgay.wutink.dtos.UserDto;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Fırat ÜLGAY
 * @since 30.01.2021
 */

@Component
public class LoginFacade {

    @Autowired
    private UserFacade userFacade;

    private static final Logger LOG = Logger.getLogger(LoginFacade.class);

    public UserDto doLogin(String userName, String password){

        UserDto userDto = userFacade.findUserByNameAndPassword(userName, password);
        GlobalMessages globalMessages = new GlobalMessages();

        if (userDto.getId() == null){
            globalMessages.setMessageType(EnumMessageType.ERROR_MESSAGE);
            globalMessages.setMessage(EnumErrorMessage.LOGIN_ERROR.getValue());
            LOG.info(userName + " --> " + EnumErrorMessage.LOGIN_ERROR.getValue());
        }else{
            globalMessages.setMessageType(EnumMessageType.CONF_MESSAGE);
            globalMessages.setMessage(EnumSuccessMessage.LOGIN_SUCCESS.getValue());
            LOG.info(userName + " --> " + EnumSuccessMessage.LOGIN_SUCCESS.getValue());
        }
        userDto.setGlobalMessage(globalMessages);
        return userDto;
    }

}
