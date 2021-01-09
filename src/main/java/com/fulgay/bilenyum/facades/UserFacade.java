package com.fulgay.bilenyum.facades;

import com.fulgay.bilenyum.commons.EnumErrorMessage;
import com.fulgay.bilenyum.commons.EnumSuccessMessage;
import com.fulgay.bilenyum.commons.GlobalMessages;
import com.fulgay.bilenyum.converter.Converter;
import com.fulgay.bilenyum.domain.User;
import com.fulgay.bilenyum.dtos.UserDto;
import com.fulgay.bilenyum.populators.UserDtoPopulator;
import com.fulgay.bilenyum.populators.UserPopulator;
import com.fulgay.bilenyum.service.UserService;
import com.fulgay.bilenyum.utils.validators.UserValidator;
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

    private static final Logger LOG = Logger.getLogger(UserFacade.class);
    private GlobalMessages globalMessage;
    private UserDto userDto;
    private Converter<User, UserDto> userDtoConverter;
    private Converter<UserDto,User> userConverter;
    private UserPopulator userPopulator;
    private UserDtoPopulator userDtoPopulator;

    public List<UserDto> findAllUsers() {
        List<User> userList = userService.findAllUsers();

        userDtoPopulator = new UserDtoPopulator();
        userDtoConverter = new Converter<>(userDtoPopulator);

        List<UserDto> userDtoList = userDtoConverter.convertToTargetList(userList);

        return userDtoList;
    }

    public UserDto findUserById(Long id) {
        User user = userService.findUserById(id);

        userDtoPopulator = new UserDtoPopulator();
        UserDto userDto = userDtoPopulator.populate(user);

        if (user == null) {
            GlobalMessages globalMessage = new GlobalMessages();
            globalMessage.setErrorMessage(EnumErrorMessage.USER_NOT_FOUND.getDisplayValue());
            userDto.setGlobalMessage(globalMessage);
        }
        return userDto;
    }

    public UserDto save(UserDto userDto) {

        try {
            boolean isUserNameValid = userValidator.validateUserByUserName(userDto.getUserName());

            if (isUserNameValid) {
                userPopulator = new UserPopulator();
                User user = userPopulator.populate(userDto);
                Long userId = userService.save(user);

                userDto.setId(userId);
                setSuccessGlobalMessage(userDto);

            } else {
                GlobalMessages globalMessage = new GlobalMessages();
                globalMessage.setErrorMessage(userDto.getUserName() + " " + EnumErrorMessage.USERNAME_ALREADY_EXIST.getDisplayValue());
                userDto.setGlobalMessage(globalMessage);
            }

        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
            globalMessage = new GlobalMessages();
            globalMessage.setErrorMessage(EnumErrorMessage.USER_COULDNT_SAVE.getDisplayValue());
            userDto.setGlobalMessage(globalMessage);
        }
        return userDto;

    }



    public UserDto findUserByUserName(String userName) {
        try {
            User user = userService.findUserByUserName(userName);

            userDtoPopulator = new UserDtoPopulator();
            userDto = userDtoPopulator.populate(user);

            if (user == null) {
                globalMessage = new GlobalMessages();
                globalMessage.setErrorMessage(EnumErrorMessage.USER_NOT_FOUND.getDisplayValue());
                userDto.setGlobalMessage(globalMessage);
            }

        } catch (Exception e) {
            globalMessage = new GlobalMessages();
            globalMessage.setErrorMessage(EnumErrorMessage.GENERAL_ERROR.getDisplayValue());
            userDto.setGlobalMessage(globalMessage);
            e.printStackTrace();
        }
        return userDto;
    }

    public UserDto findUserByNameAndPassword(String userName, String password) {
        User user = userService.findUserByUserNameAndPassword(userName, password);

        userDtoPopulator = new UserDtoPopulator();
        UserDto userDto = userDtoPopulator.populate(user);

        return userDto;
    }

    private void setSuccessGlobalMessage(UserDto userDto) {
        globalMessage = new GlobalMessages();
        globalMessage.setConfMessage(userDto.getUserName() + " " + EnumSuccessMessage.USER_SAVE_SUCCESS.getDisplayValue());
        userDto.setGlobalMessage(globalMessage);
        LOG.info(userDto.getUserName() + " " + EnumSuccessMessage.USER_SAVE_SUCCESS.getDisplayValue());
    }
}
