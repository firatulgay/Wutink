package com.fulgay.wutink.facades;

import com.fulgay.wutink.commons.notificationMessages.EnumErrorMessage;
import com.fulgay.wutink.commons.notificationMessages.EnumMessageType;
import com.fulgay.wutink.commons.notificationMessages.EnumSuccessMessage;
import com.fulgay.wutink.commons.notificationMessages.GlobalMessages;
import com.fulgay.wutink.commons.wutinkExceptions.WutinkUserSaveException;
import com.fulgay.wutink.dtos.UserDto;
import com.fulgay.wutink.security.model.RegistrationResponse;
import com.fulgay.wutink.service.AuthenticationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

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

    public RegistrationResponse doRegister(UserDto userDto, HttpServletResponse httpServletResponse) {
        RegistrationResponse registrationResponse = new RegistrationResponse();

        try {
            addUserRoles(userDto);
            userFacade.save(userDto);
            authenticationService.authenticate("Basic " + Base64.getEncoder().encodeToString(
                    (userDto.getUserName() + ":" + userDto.getPassword()).getBytes()),httpServletResponse);

            setSuccessGlobalMessage(userDto,registrationResponse);
            LOG.info("Registration successful with username : " + userDto.getUserName());

        } catch (WutinkUserSaveException ex) {
            globalMessage = new GlobalMessages(EnumMessageType.WARN_MESSAGE,userDto.getUserName() + " " + ex.getMessage());
            registrationResponse.setGlobalMessage(globalMessage);
            registrationResponse.setSuccess(false);
            LOG.warn("Registration failed with username : " + userDto.getUserName() + " " + ex.getMessage());
        }catch (Exception e){
            LOG.error("Error while doing registration : " + userDto.getUserName(),e);
            globalMessage = new GlobalMessages(EnumMessageType.ERROR_MESSAGE, EnumErrorMessage.GENERAL_ERROR.getValue());
            registrationResponse.setGlobalMessage(globalMessage);
            registrationResponse.setSuccess(false);
        }

        return registrationResponse;
    }

    private void addUserRoles(UserDto userDto) {
        List<String> userRoles = new ArrayList<>();
        userRoles.add("USER");
        userDto.setUserRoles(userRoles);
    }

    private void setSuccessGlobalMessage(UserDto userDto, RegistrationResponse registrationResponse) {
        globalMessage = new GlobalMessages(EnumMessageType.CONF_MESSAGE,EnumSuccessMessage.REGISTER_SUCCESS.getValue());
        registrationResponse.setGlobalMessage(globalMessage);
        registrationResponse.setSuccess(true);
        registrationResponse.setUserName(userDto.getUserName());
    }
}
