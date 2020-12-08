package com.fulgay.bilenyum.populators;

import com.fulgay.bilenyum.domain.User;
import com.fulgay.bilenyum.dtos.userDto.UserDto;
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
}
