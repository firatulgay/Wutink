package com.fulgay.wutink.facades;

import com.fulgay.wutink.commons.notificationMessages.EnumErrorMessage;
import com.fulgay.wutink.commons.notificationMessages.EnumMessageType;
import com.fulgay.wutink.commons.notificationMessages.EnumSuccessMessage;
import com.fulgay.wutink.commons.notificationMessages.GlobalMessages;
import com.fulgay.wutink.converter.UserConverter;
import com.fulgay.wutink.converter.UserDtoConverter;
import com.fulgay.wutink.domain.User;
import com.fulgay.wutink.dtos.UserDto;
import com.fulgay.wutink.service.AuthenticationService;
import com.fulgay.wutink.service.SessionService;
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
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private SessionService sessionService;

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
            GlobalMessages globalMessage = new GlobalMessages(EnumMessageType.ERROR_MESSAGE,EnumErrorMessage.USER_NOT_FOUND.getValue());
            userDto.setGlobalMessage(globalMessage);
        }
        return userDto;
    }

    public UserDto save(UserDto userDto) {
            userValidator.validateUserByUserName(userDto.getUserName());

            User user = userConverter.convert(userDto);
            userService.save(user);

            LOG.info(userDto.getUserName() + " " + EnumSuccessMessage.REGISTER_SUCCESS.getValue());
        return userDto;
    }


    public UserDto findUserByUserName(String userName) {

        try {
            User user = userService.findUserByUserName(userName);
            userDto = userDtoConverter.convert(user);

            if (user == null) {
                GlobalMessages globalMessage = new GlobalMessages(EnumMessageType.ERROR_MESSAGE,EnumErrorMessage.USER_NOT_FOUND.getValue());
                userDto.setGlobalMessage(globalMessage);
            }

        } catch (Exception e) {
            GlobalMessages globalMessage = new GlobalMessages(EnumMessageType.ERROR_MESSAGE,EnumErrorMessage.GENERAL_ERROR.getValue());
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

    public UserDto getCurrentAuthUser(){
        String userName = sessionService.getSessionUserName();
        return userDtoConverter.convert(userService.findUserByUserName(userName));
    }


}
