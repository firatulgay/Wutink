package com.fulgay.wutink.service.impl;

import com.fulgay.wutink.security.jwt.manager.JwtTokenManager;
import com.fulgay.wutink.security.model.WutinkAuthenticationResponse;
import com.fulgay.wutink.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenManager tokenManager;

    @Override
    public WutinkAuthenticationResponse authenticate(String authorization) {

        if(!StringUtils.hasText(authorization)) {
            throw new RuntimeException("INVALID AUTH PAYLOAD");
        }

        String[] httpBasicAuthPayload = parseHttpBasicPayload(authorization);
        String username = httpBasicAuthPayload[0];
        String password = httpBasicAuthPayload[1];

        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        User user = (User) authenticate.getPrincipal();
        if(user == null) {
            throw new RuntimeException("USER NOT FOUND");
        }

        com.fulgay.wutink.domain.User userFromDb = userService.findUserByUserName(username);
        String[] tokenArray = tokenManager.generateToken(user, userFromDb.getId());
        return new WutinkAuthenticationResponse(userFromDb.getId(), tokenArray[0],tokenArray[1],Boolean.TRUE);
    }

    private String[] parseHttpBasicPayload(String authorization) {

        if (authorization != null && authorization.toLowerCase().startsWith("basic")) {

            String base64Credentials = authorization.substring("Basic".length()).trim();
            byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
            String credentials = new String(credDecoded, StandardCharsets.UTF_8);
            return credentials.split(":", 2);
        }
        return null;
    }
}
