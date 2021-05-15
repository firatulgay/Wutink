package com.fulgay.wutink.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * User
 *
 * @author Fırat ÜLGAY
 * @since 5.230.0
 */

@Entity
@Table(name = "USER", indexes = {
        @Index(name = "IX_USER_NAME",columnList = "user_Name", unique = true)})
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column
    private Long id;

    @NotBlank
    @Column()
    private String password;

    @NotBlank
    @Size(max = 50)
    @Column(name = "user_name",length = 50)
    private String userName;

    @Column(name = "user_roles",length = 50)
    private String userRoles;

    @Column(name = "user_permisson",length = 50)
    private String userPermissions;

    @Column(name = "active")
    private boolean isActive;

    public User() {
    }

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

    public List<String> getUserPermissions() {
        return convertToList(userPermissions);
    }

    public void setUserPermissions(String userPermissions) {
        this.userPermissions = userPermissions;
    }

    public List<String> getUserRoles() {
        return convertToList(userRoles);   }

    public void setUserRoles(String userRoles) {
        this.userRoles = userRoles;
    }

    private List<String> convertToList(String obj){
        if (obj.length() > 0){
            return Arrays.asList(obj.split(","));
        }
        return new ArrayList<String>();
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
