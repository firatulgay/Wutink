package com.fulgay.wutink.facades;

import com.fulgay.wutink.commons.notificationMessages.EnumMessageType;
import com.fulgay.wutink.commons.notificationMessages.EnumSuccessMessage;
import com.fulgay.wutink.commons.notificationMessages.GlobalMessages;
import com.fulgay.wutink.commons.wutinkExceptions.WutinkUserSaveException;
import com.fulgay.wutink.dtos.UserDto;
import com.fulgay.wutink.security.model.RegistrationResponse;
import com.fulgay.wutink.security.model.WutinkAuthenticationResponse;
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

    public RegistrationResponse doRegister(UserDto userDto) {
        RegistrationResponse registrationResponse = new RegistrationResponse();

        try {
            userFacade.save(userDto);

            WutinkAuthenticationResponse authResponse = authenticationService.authenticate("Basic " + Base64.getEncoder().encodeToString(
                    (userDto.getUserName() + ":" + userDto.getPassword()).getBytes()));

            registrationResponse.setAccessToken(authResponse.getAccessToken());
            registrationResponse.setRefreshToken(authResponse.getRefreshToken());

            setSuccessGlobalMessage(registrationResponse);
            LOG.info("Registration successful with username : " + userDto.getUserName());

        } catch (WutinkUserSaveException ex) {
            globalMessage = new GlobalMessages(EnumMessageType.ERROR_MESSAGE,userDto.getUserName() + " " + ex.getMessage());
            registrationResponse.setGlobalMessage(globalMessage);
            registrationResponse.setSuccess(false);
            LOG.warn("Registration failed with username : " + userDto.getUserName() + " " + ex.getMessage());
        }

        return registrationResponse;
    }

    private void setSuccessGlobalMessage(RegistrationResponse registrationResponse) {
        globalMessage = new GlobalMessages(EnumMessageType.CONF_MESSAGE,EnumSuccessMessage.REGISTER_SUCCESS.getValue());
        registrationResponse.setGlobalMessage(globalMessage);
        registrationResponse.setSuccess(true);
    }
}
