package com.fulgay.wutink.security.model;

import com.fulgay.wutink.dtos.BaseDto;

import java.io.Serializable;


public class RegistrationResponse extends BaseDto implements Serializable {

    private static final long serialVersionUID = 8929499253726531083L;

    private Long userId;
    private String token;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
