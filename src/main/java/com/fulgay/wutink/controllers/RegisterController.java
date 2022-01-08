package com.fulgay.wutink.controllers;

import com.fulgay.wutink.dtos.UserDto;
import com.fulgay.wutink.facades.RegisterFacade;
import com.fulgay.wutink.security.model.RegistrationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
public class RegisterController {

    @Autowired
    private RegisterFacade registerFacade;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public RegistrationResponse register (@RequestBody UserDto userDto, HttpServletResponse httpResponse){
        RegistrationResponse registrationResponse = registerFacade.doRegister(userDto);

        Cookie cookieAccess = new Cookie("jwtSessionId", registrationResponse.getAccessToken());
        cookieAccess.setHttpOnly(Boolean.TRUE);
        cookieAccess.setSecure(Boolean.TRUE);
        httpResponse.addCookie(cookieAccess);

        Cookie cookieRefresh = new Cookie("refreshToken", registrationResponse.getRefreshToken());
        cookieRefresh.setHttpOnly(Boolean.TRUE);
        cookieRefresh.setSecure(Boolean.TRUE);
        httpResponse.addCookie(cookieRefresh);

        return registrationResponse;
    }
}
