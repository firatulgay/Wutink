package com.fulgay.wutink.controllers;

import com.fulgay.wutink.commons.notificationMessages.EnumMessageType;
import com.fulgay.wutink.commons.notificationMessages.GlobalMessages;
import com.fulgay.wutink.security.model.AuthenticationResponse;
import com.fulgay.wutink.service.AuthenticationService;
import com.fulgay.wutink.utils.config.ConfigurationUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private ConfigurationUtil configurationUtil;

    private static final Logger LOG = Logger.getLogger(LoginController.class);

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<AuthenticationResponse> login(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {

        AuthenticationResponse response = null;

        try {
            response = authenticationService.authenticate(authorization);
            LOG.info("LOGIN SUCCESSFUL!");

            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, response.getToken())
                    .body(response);

        } catch (BadCredentialsException ex) {
            LOG.error("LOGIN FAIL --> " + ex.getMessage());
            response = new AuthenticationResponse(0L, "");
            response.setGlobalMessage(new GlobalMessages(EnumMessageType.ERROR_MESSAGE, configurationUtil.getGeneralMessagesProperty().getProperty("login.error")));
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}
