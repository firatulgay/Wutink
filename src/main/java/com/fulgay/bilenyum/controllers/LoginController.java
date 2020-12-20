package com.fulgay.bilenyum.controllers;

import com.fulgay.bilenyum.commons.EnumErrorMessage;
import com.fulgay.bilenyum.commons.EnumSuccessMessage;
import com.fulgay.bilenyum.commons.GlobalMessages;
import com.fulgay.bilenyum.dtos.UserDto;
import com.fulgay.bilenyum.facades.UserFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
public class LoginController {


    @Autowired
    UserFacade userFacade;

    private static final Logger LOG = Logger.getLogger(UserFacade.class);

    @GetMapping("/login")
    public UserDto login(@PathParam("userName") String userName,
                         @PathParam("password") String password){

        UserDto userDto = userFacade.findUserByNameAndPassword(userName, password);
        GlobalMessages globalMessages = new GlobalMessages();

        if (userDto.getId() == null){
            globalMessages.setErrorMessage(EnumErrorMessage.LOGIN_ERROR.getDisplayValue());
            LOG.info(userName + " --> " + EnumErrorMessage.LOGIN_ERROR.getDisplayValue());
        }else{
            globalMessages.setConfMessage(EnumSuccessMessage.LOGIN_SUCCESS.getDisplayValue());
            LOG.info(userName + " --> " + EnumSuccessMessage.LOGIN_SUCCESS.getDisplayValue());
        }
        userDto.setGlobalMessage(globalMessages);
        return userDto;
    }
}
