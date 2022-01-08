package com.fulgay.wutink.service;

import com.fulgay.wutink.security.model.WutinkAuthenticationResponse;

public interface AuthenticationService {
    WutinkAuthenticationResponse authenticate(String authorization);
}
