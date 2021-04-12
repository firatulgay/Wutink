package com.fulgay.wutink.controllers;

import com.fulgay.wutink.dtos.UserDto;
import com.fulgay.wutink.facades.LoginFacade;
import com.fulgay.wutink.facades.UserFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

@RestController
public class LoginController {


    @Autowired
    private LoginFacade loginFacade;

    private static final Logger LOG = Logger.getLogger(UserFacade.class);

    @GetMapping("/login")
    public UserDto login(@PathParam("userName") String userName,
                         @PathParam("password") String password, HttpServletRequest httpServletRequest){

        UserDto userDto = loginFacade.doLogin(userName, password);
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("username",userDto.getUserName());
        session.setAttribute("userType",userDto.getUserType());
        return userDto;
    }
}
