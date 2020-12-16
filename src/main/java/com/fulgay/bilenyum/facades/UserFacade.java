package com.fulgay.bilenyum.facades;

import com.fulgay.bilenyum.commons.EnumErrorMessage;
import com.fulgay.bilenyum.commons.EnumSuccessMessage;
import com.fulgay.bilenyum.commons.ErrorDto;
import com.fulgay.bilenyum.domain.User;
import com.fulgay.bilenyum.dtos.UserDto;
import com.fulgay.bilenyum.populators.UserPopulator;
import com.fulgay.bilenyum.service.UserService;
import com.fulgay.bilenyum.utils.validators.UserValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserFacade {


    private static final Logger LOGGER = Logger.getLogger(UserFacade.class);

    @Autowired
    UserValidator userValidator;

    @Autowired
    UserPopulator userPopulator;

    @Autowired
    UserService userService;

    private static final Logger LOG = Logger.getLogger(UserFacade.class);


    public List<UserDto> findAllUsers() {
        List<User> userList = userService.findAllUsers();
        List<UserDto> userDtoList = userPopulator.populateUserDtoList(userList);

        return userDtoList;
    }

    public UserDto findUserById(Long id) {
        User user = userService.findUserById(id);
        UserDto userDto = userPopulator.populateUserDto(user);

        if (user == null){
            ErrorDto errorDto = new ErrorDto();
            errorDto.setMessage(EnumErrorMessage.USER_NOT_FOUND.getDisplayValue());
            errorDto.setCode(EnumErrorMessage.USER_NOT_FOUND.getCode());
            userDto.setErrorDto(errorDto);
        }
        return userDto;
    }

    public UserDto save(UserDto userDto) {

        try {
            boolean isUserNameValid = userValidator.validateUserByUserName(userDto.getUserName());

            if (isUserNameValid) {
                User user = userPopulator.populateUser(userDto);
                Long userId = userService.save(user);

                userDto.setId(userId);
                userDto.setMessage(userDto.getUserName() + " " + EnumSuccessMessage.USER_SAVE_SUCCESS.getDisplayValue());
                LOG.info(userDto.getUserName() + " " + EnumSuccessMessage.USER_SAVE_SUCCESS.getDisplayValue());

            } else {
                ErrorDto errorDto = new ErrorDto();
                errorDto.setMessage(userDto.getUserName() + " " +EnumErrorMessage.USERNAME_ALREADY_EXIST.getDisplayValue());
                userDto.setErrorDto(errorDto);
            }

        }catch (Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return userDto;

    }

    public UserDto findUserByUserName(String userName) {
        User user = userService.findUserByUserName(userName);
        UserDto userDto = userPopulator.populateUserDto(user);

        if (user == null){
            ErrorDto errorDto = new ErrorDto();
            errorDto.setMessage(EnumErrorMessage.USER_NOT_FOUND.getDisplayValue());
            errorDto.setCode(EnumErrorMessage.USER_NOT_FOUND.getCode());
            userDto.setErrorDto(errorDto);
        }
        return userDto;
    }
}
