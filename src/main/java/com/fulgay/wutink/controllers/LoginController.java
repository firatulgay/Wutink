package com.fulgay.wutink.controllers;

import com.fulgay.wutink.commons.notificationMessages.EnumMessageType;
import com.fulgay.wutink.commons.notificationMessages.GlobalMessages;
import com.fulgay.wutink.security.model.WutinkAuthenticationResponse;
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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginController {

    @Autowired
    private AuthenticationService authenticationService;

    private static final Logger LOG = Logger.getLogger(LoginController.class);

    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    public ResponseEntity<WutinkAuthenticationResponse> doLogin(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization, HttpServletResponse httpResponse) {

        WutinkAuthenticationResponse response = null;

        try {
            response = authenticationService.authenticate(authorization,httpResponse);

            LOG.info("LOGIN SUCCESSFUL!");
            response.setGlobalMessage(new GlobalMessages(EnumMessageType.CONF_MESSAGE, ConfigurationUtil.getGeneralMessagesProperty().getProperty("login.success")));
            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, response.getAccessToken())
                    .body(response);

        } catch (BadCredentialsException ex) {
            LOG.error("LOGIN FAIL :: " + ex.getMessage());
            response = new WutinkAuthenticationResponse(0L, "","", Boolean.FALSE);
            response.setGlobalMessage(new GlobalMessages(EnumMessageType.ERROR_MESSAGE,ConfigurationUtil.getGeneralMessagesProperty().getProperty("login.error")));
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @RequestMapping(value = "/doLogout", method = RequestMethod.POST)
    public ResponseEntity doLogout(HttpServletResponse httpResponse){
        Cookie cookieAccess = new Cookie("jwtSessionId", null);
        cookieAccess.setMaxAge(0);

        Cookie cookieRefresh = new Cookie("refreshToken", null);
        cookieRefresh.setMaxAge(0);

        httpResponse.addCookie(cookieAccess);
        httpResponse.addCookie(cookieRefresh);

        return ResponseEntity.ok().body("");
    }
}
