package com.fulgay.bilenyum.controllers;

import com.fulgay.bilenyum.dtos.UserDto;
import com.fulgay.bilenyum.facades.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/register")
public class RegisterController {

    @Autowired
    private UserFacade userFacade;

    public UserDto register (@RequestBody UserDto userDto){
        UserDto savedUser = userFacade.save(userDto);
        return savedUser;
    }
}
