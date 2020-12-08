package com.fulgay.bilenyum.controllers;

import com.fulgay.bilenyum.domain.User;
import com.fulgay.bilenyum.dtos.userDto.UserDto;
import com.fulgay.bilenyum.facades.UserFacade;
import com.fulgay.bilenyum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserFacade userFacade;

//    @PostMapping("/userDto")
//    public User saveUser(@RequestBody UserDto userDto){
//            userService.save(userDto);
//    }

    @GetMapping("/getUsers")
    public List<UserDto> findAllUsers(){
        List<UserDto> allUsers = userFacade.findAllUsers();
        return allUsers;
    }

    @GetMapping("/getUserById")
    public UserDto findAllUsers(@PathParam("id") Long id){
        UserDto userById = userFacade.findUserById(id);
        return userById;
    }


}
