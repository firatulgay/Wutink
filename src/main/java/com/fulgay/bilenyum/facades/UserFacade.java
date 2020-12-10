package com.fulgay.bilenyum.facades;

import com.fulgay.bilenyum.domain.User;
import com.fulgay.bilenyum.dtos.UserDto;
import com.fulgay.bilenyum.enums.EnumErrorMessage;
import com.fulgay.bilenyum.enums.EnumSuccessMessage;
import com.fulgay.bilenyum.populators.UserPopulator;
import com.fulgay.bilenyum.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public abstract class UserFacade {


   private static final Logger LOGGER = Logger.getLogger(UserFacade.class);

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

    public UserDto save(UserDto userDto) {
        User user = userPopulator.populateUser(userDto);
        Long userId = userService.save(user);

        if (userId != null){
            userDto.setId(userId);
            userDto.setMessage(EnumErrorMessage.USER_COULDNT_SAVE.getDisplayValue());
            LOGGER.error(EnumErrorMessage.USER_COULDNT_SAVE.getDisplayValue());
        }else {
            userDto.setMessage(EnumSuccessMessage.USER_SAVE_SUCCESS.getDisplayValue());
            LOGGER.info(userId +"id'li " + EnumSuccessMessage.USER_SAVE_SUCCESS.getDisplayValue());
        }

        return userDto;
    }
}
