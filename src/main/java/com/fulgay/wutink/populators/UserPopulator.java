package com.fulgay.wutink.populators;

import com.fulgay.wutink.domain.User;
import com.fulgay.wutink.dtos.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserPopulator implements Populator<UserDto, User> {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void populate(UserDto source, User target) {
        if (source != null) {
            target.setUserName(source.getUserName());
            target.setPassword(passwordEncoder.encode(source.getPassword()));
        }
    }
}
