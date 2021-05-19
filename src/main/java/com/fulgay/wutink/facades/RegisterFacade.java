package com.fulgay.wutink.facades;

import com.fulgay.wutink.commons.notificationMessages.EnumSuccessMessage;
import com.fulgay.wutink.commons.notificationMessages.GlobalMessages;
import com.fulgay.wutink.commons.wutinkExceptions.WutinkUserSaveException;
import com.fulgay.wutink.dtos.UserDto;
import com.fulgay.wutink.security.model.AuthenticationResponse;
import com.fulgay.wutink.service.AuthenticationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Base64;

/**
 * @author Fırat ÜLGAY
 * @since 20.05.2021
 */

@Component
public class RegisterFacade {

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private AuthenticationService authenticationService;

    private static final Logger LOG = Logger.getLogger(UserFacade.class);
    private GlobalMessages globalMessage;

    public UserDto doRegister(UserDto userDto) {

        try {
            userFacade.save(userDto);

            AuthenticationResponse authenticate = authenticationService.authenticate(
                    "Basic " +
                            Base64.getEncoder().encodeToString
                                    ((userDto.getUserName() + ":" + userDto.getPassword()).getBytes()));

            userDto.setAuthToken(authenticate.getToken());
            userDto.setSuccess(true);
            setSuccessGlobalMessage(userDto);

            LOG.info("Registration successful with username : " + userDto.getUserName());

        } catch (WutinkUserSaveException ex) {
            globalMessage = new GlobalMessages();
            globalMessage.setErrorMessage(userDto.getUserName() + " " + ex.getMessage());
            userDto.setGlobalMessage(globalMessage);
            LOG.warn("Registration failed with username : " + userDto.getUserName() + " " + ex.getMessage());
        }

        return userDto;
    }

    private void setSuccessGlobalMessage(UserDto userDto) {
        globalMessage = new GlobalMessages();
        globalMessage.setConfMessage(userDto.getUserName() + " " + EnumSuccessMessage.USER_SAVE_SUCCESS.getValue());
        userDto.setGlobalMessage(globalMessage);
    }
}
