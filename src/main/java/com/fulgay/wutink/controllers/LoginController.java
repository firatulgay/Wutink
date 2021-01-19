package com.fulgay.wutink.controllers;

import com.fulgay.wutink.commons.EnumErrorMessage;
import com.fulgay.wutink.commons.EnumSuccessMessage;
import com.fulgay.wutink.commons.GlobalMessages;
import com.fulgay.wutink.dtos.UserDto;
import com.fulgay.wutink.facades.UserFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
public class LoginController {


    @Autowired
    private UserFacade userFacade;

    private static final Logger LOG = Logger.getLogger(UserFacade.class);

    @GetMapping("/login")
    public UserDto login(@PathParam("userName") String userName,
                         @PathParam("password") String password){

        UserDto userDto = userFacade.findUserByNameAndPassword(userName, password);
        GlobalMessages globalMessages = new GlobalMessages();

        if (userDto.getId() == null){
            globalMessages.setErrorMessage(EnumErrorMessage.LOGIN_ERROR.getValue());
            LOG.info(userName + " --> " + EnumErrorMessage.LOGIN_ERROR.getValue());
        }else{
            globalMessages.setConfMessage(EnumSuccessMessage.LOGIN_SUCCESS.getValue());
            LOG.info(userName + " --> " + EnumSuccessMessage.LOGIN_SUCCESS.getValue());
        }
        userDto.setGlobalMessage(globalMessages);
        return userDto;
    }
}
