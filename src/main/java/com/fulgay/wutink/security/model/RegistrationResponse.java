package com.fulgay.wutink.security.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fulgay.wutink.dtos.BaseDto;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RegistrationResponse extends BaseDto implements Serializable {

    private static final long serialVersionUID = 8929499253726531083L;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private Long userId;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String accessToken;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String refreshToken;

    private String userName;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
