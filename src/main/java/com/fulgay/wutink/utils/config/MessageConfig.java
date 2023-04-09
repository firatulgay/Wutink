package com.fulgay.wutink.utils.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:generalMessages-tr.properties")
@Configuration
public class MessageConfig {

    @Value("${login.error:}")
    private String loginErrorMessage;

    @Value("${login.success:}")
    private String loginSuccessMessage;

    public String getLoginErrorMessage() {
        return loginErrorMessage;
    }

    public void setLoginErrorMessage(String loginErrorMessage) {
        this.loginErrorMessage = loginErrorMessage;
    }

    public String getLoginSuccessMessage() {
        return loginSuccessMessage;
    }

    public void setLoginSuccessMessage(String loginSuccessMessage) {
        this.loginSuccessMessage = loginSuccessMessage;
    }
}
