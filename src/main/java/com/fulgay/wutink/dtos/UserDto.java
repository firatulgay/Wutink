package com.fulgay.wutink.dtos;

import com.fulgay.wutink.enums.EnumUserType;

public class UserDto extends BaseDto {
    private Long id;
    private String password;
    private String userName;
    private EnumUserType userType;

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

    public EnumUserType getUserType() {
        return userType;
    }

    public void setUserType(EnumUserType userType) {
        this.userType = userType;
    }

}
