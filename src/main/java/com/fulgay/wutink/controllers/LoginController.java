package com.fulgay.wutink.controllers;

import com.fulgay.wutink.facades.LoginFacade;
import com.fulgay.wutink.facades.UserFacade;
import com.fulgay.wutink.security.model.AuthenticationResponse;
import com.fulgay.wutink.service.AuthenticationService;
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
    private AuthenticationService authenticationServiceImpl;

    @Autowired
    private LoginFacade loginFacade;

    private static final Logger LOG = Logger.getLogger(UserFacade.class);

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<AuthenticationResponse> login(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization){
        try {
            AuthenticationResponse response = authenticationServiceImpl.authenticate(authorization);
            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, response.getToken())
                    .body(response);

        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
