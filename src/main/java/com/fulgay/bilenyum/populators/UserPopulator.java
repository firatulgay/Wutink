package com.fulgay.bilenyum.populators;

import com.fulgay.bilenyum.domain.User;
import com.fulgay.bilenyum.dtos.UserDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserPopulator {
    public List<UserDto> populateUserDtoList(List<User> userList){
        List<UserDto> userDtoList = new ArrayList<>();
        userList.stream().forEach(user -> userDtoList.add(populateUserDto(user)));
        return userDtoList;
    }

    public UserDto populateUserDto(User user) {
        UserDto userDto = new UserDto();

        userDto.setUserName(user.getUserName());
        userDto.setId(user.getId());
        userDto.setUserType(user.getUserType());
        userDto.setName(user.getName());
        userDto.setPassword(user.getPassword());
        userDto.setSurname(user.getSurname());

        return userDto;
    }

    public User populateUser(UserDto userDto){
        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setUserType(userDto.getUserType());
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        user.setSurname(userDto.getSurname());

        return user;
    }
}
