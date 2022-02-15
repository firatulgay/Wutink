package com.fulgay.wutink.service;

import com.fulgay.wutink.security.model.WutinkAuthenticationResponse;

import javax.servlet.http.HttpServletResponse;

public interface AuthenticationService {
    WutinkAuthenticationResponse authenticate(String authorization, HttpServletResponse httpResponse);
    void addAuthCookies(String[] tokens, HttpServletResponse httpResponse);
}
