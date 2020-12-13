package com.fulgay.bilenyum.populators;

import com.fulgay.bilenyum.domain.User;
import com.fulgay.bilenyum.dtos.UserDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserPopulator {

    private UserDto userDto;
    private User user;
    List<UserDto> userDtoList ;

    public List<UserDto> populateUserDtoList(List<User> userList){

        if (userList != null && userList.size() > 0){
            userDtoList = new ArrayList<>();
            for (User user : userList) {
                userDtoList.add(populateUserDto(user));  // userList.stream().forEach(user -> userDtoList.add(populateUserDto(user)));
            }
        }
        return userDtoList;
    }

    public UserDto populateUserDto(User user) {

        if(user != null){
            userDto = new UserDto();
            userDto.setUserName(user.getUserName());
            userDto.setId(user.getId());
            userDto.setUserType(user.getUserType());
            userDto.setName(user.getName());
            userDto.setPassword(user.getPassword());
            userDto.setSurname(user.getSurname());
        }
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
