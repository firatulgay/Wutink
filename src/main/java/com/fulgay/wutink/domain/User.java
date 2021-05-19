package com.fulgay.wutink.domain;

import com.fulgay.wutink.enums.EnumUserType;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;


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
    @Column(length = 50)
    private String password;

    @NotBlank
    @Size(max = 50)
    @Column(name = "user_name",length = 50)
    private String userName;

    @Column(name = "user_type",length = 50)
    @Enumerated(EnumType.STRING)
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
