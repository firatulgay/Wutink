package com.fulgay.bilenyum.dtos;

import com.fulgay.bilenyum.enums.EnumUserType;

public class UserDto extends BaseDto {
    private Long id;
    private String name;
    private String surname;
    private String password;
    private String userName;
    private EnumUserType userType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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