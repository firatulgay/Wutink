package com.fulgay.bilenyum.facades;

import com.fulgay.bilenyum.domain.User;
import com.fulgay.bilenyum.dtos.userDto.UserDto;
import com.fulgay.bilenyum.populators.UserPopulator;
import com.fulgay.bilenyum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserFacade {
    @Autowired
    UserPopulator userPopulator;

    @Autowired
    UserService userService;

    public List<UserDto> findAllUsers(){
        List<User> userList = userService.findAllUsers();
        List<UserDto> userDtoList = userPopulator.populateUserDtoList(userList);

        return userDtoList;
    }

    public UserDto findUserById(Long id){
        User user = userService.findUserById(id);
        return userPopulator.populateUserDto(user);
    }
}
