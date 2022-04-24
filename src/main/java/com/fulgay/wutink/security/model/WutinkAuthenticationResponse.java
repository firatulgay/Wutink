package com.fulgay.wutink.security.model;

import com.fulgay.wutink.dtos.BaseDto;

import java.io.Serializable;


public class WutinkAuthenticationResponse extends BaseDto implements Serializable  {

    private static final long serialVersionUID = 8929499253726531083L;

    private String userName;
    private String accessToken;
    private String refreshToken;

    public WutinkAuthenticationResponse(String userName, String accessToken, String refreshToken, boolean isSuccess) {
        this.userName = userName;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        setSuccess(isSuccess);
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
