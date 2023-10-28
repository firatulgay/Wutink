package com.fulgay.wutink.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class UserDto extends BaseDto {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Long id;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String password;
    private String userName;
    private List<String> userRoles;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String authToken;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<String> userRoles) {
        this.userRoles = userRoles;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
