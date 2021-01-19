package com.fulgay.wutink.facades;

import com.fulgay.wutink.commons.EnumErrorMessage;
import com.fulgay.wutink.commons.EnumSuccessMessage;
import com.fulgay.wutink.commons.GlobalMessages;
import com.fulgay.wutink.converter.UserConverter;
import com.fulgay.wutink.converter.UserDtoConverter;
import com.fulgay.wutink.domain.User;
import com.fulgay.wutink.dtos.UserDto;
import com.fulgay.wutink.service.UserService;
import com.fulgay.wutink.utils.validators.UserValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserFacade {

    @Autowired
    private UserValidator userValidator;
    @Autowired
    private UserService userService;
    @Autowired
    private UserDtoConverter userDtoConverter;
    @Autowired
    private UserConverter userConverter;

    private static final Logger LOG = Logger.getLogger(UserFacade.class);
    private GlobalMessages globalMessage;
    private UserDto userDto;

    public List<UserDto> findAllUsers() {
        List<User> userList = userService.findAll();
        List<UserDto> userDtoList = userDtoConverter.convertToList(userList);

        return userDtoList;
    }

    public UserDto findUserById(Long id) {
        User user = userService.findById(id);
        UserDto userDto = userDtoConverter.convert(user);

        if (user == null) {
            GlobalMessages globalMessage = new GlobalMessages();
            globalMessage.setErrorMessage(EnumErrorMessage.USER_NOT_FOUND.getValue());
            userDto.setGlobalMessage(globalMessage);
        }
        return userDto;
    }

    public UserDto save(UserDto userDto) {

        try {
            boolean isUserNameValid = userValidator.validateUserByUserName(userDto.getUserName());

            if (isUserNameValid) {
                User user = userConverter.convert(userDto);
                Long userId = userService.save(user);

                userDto.setId(userId);
                setSuccessGlobalMessage(userDto);
            } else {
                GlobalMessages globalMessage = new GlobalMessages();
                globalMessage.setErrorMessage(userDto.getUserName() + " " + EnumErrorMessage.USERNAME_ALREADY_EXIST.getValue());
                userDto.setGlobalMessage(globalMessage);
            }

        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
            globalMessage = new GlobalMessages();
            globalMessage.setErrorMessage(EnumErrorMessage.USER_COULDNT_SAVE.getValue());
            userDto.setGlobalMessage(globalMessage);
        }
        return userDto;

    }


    public UserDto findUserByUserName(String userName) {

        try {
            User user = userService.findUserByUserName(userName);
            userDto = userDtoConverter.convert(user);

            if (user == null) {
                globalMessage = new GlobalMessages();
                globalMessage.setErrorMessage(EnumErrorMessage.USER_NOT_FOUND.getValue());
                userDto.setGlobalMessage(globalMessage);
            }

        } catch (Exception e) {
            globalMessage = new GlobalMessages();
            globalMessage.setErrorMessage(EnumErrorMessage.GENERAL_ERROR.getValue());
            userDto.setGlobalMessage(globalMessage);
            e.printStackTrace();
        }
        return userDto;
    }

    public UserDto findUserByNameAndPassword(String userName, String password) {
        User user = userService.findUserByUserNameAndPassword(userName, password);
        UserDto userDto = userDtoConverter.convert(user);

        return userDto;
    }

    private void setSuccessGlobalMessage(UserDto userDto) {
        globalMessage = new GlobalMessages();
        globalMessage.setConfMessage(userDto.getUserName() + " " + EnumSuccessMessage.USER_SAVE_SUCCESS.getValue());
        userDto.setGlobalMessage(globalMessage);
        LOG.info(userDto.getUserName() + " " + EnumSuccessMessage.USER_SAVE_SUCCESS.getValue());
    }
}
