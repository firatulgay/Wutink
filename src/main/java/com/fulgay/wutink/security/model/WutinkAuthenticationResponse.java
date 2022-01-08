package com.fulgay.wutink.security.model;

import com.fulgay.wutink.dtos.BaseDto;

import java.io.Serializable;


public class WutinkAuthenticationResponse extends BaseDto implements Serializable  {

    private static final long serialVersionUID = 8929499253726531083L;

    private Long userId;
    private String accessToken;
    private String refreshToken;

    public WutinkAuthenticationResponse(Long userId, String accessToken, String refreshToken) {
        this.userId = userId;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
