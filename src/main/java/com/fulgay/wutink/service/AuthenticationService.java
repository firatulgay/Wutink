package com.fulgay.wutink.service;

import com.fulgay.wutink.security.model.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse authenticate(String authorization);
}
