package com.fulgay.wutink.controllers;

import com.fulgay.wutink.dtos.UserDto;
import com.fulgay.wutink.facades.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.security.Principal;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserFacade userFacade;

    @PostMapping("/saveUser")
    public UserDto saveUser(@RequestBody UserDto userDto){
        UserDto savedUser = userFacade.save(userDto);
        return savedUser;
    }

    @GetMapping("/getUsers")
    public List<UserDto> findAllUsers(HttpServletRequest httpServletRequest){
        List<UserDto> allUsers = userFacade.findAllUsers();
        Principal userPrincipal = httpServletRequest.getUserPrincipal();
        return allUsers;
    }

    @GetMapping("/getUserById")
    public UserDto findUserById(@PathParam("id") Long id){
        UserDto userById = userFacade.findUserById(id);
        return userById;
    }

    @GetMapping("/getUserByUserName")
    public UserDto findUserByUserName(@PathParam("userName") String userName){
        UserDto userDto = userFacade.findUserByUserName(userName);
        return userDto;
    }


}
