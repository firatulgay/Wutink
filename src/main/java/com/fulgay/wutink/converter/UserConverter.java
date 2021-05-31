package com.fulgay.wutink.converter;

import com.fulgay.wutink.domain.User;
import com.fulgay.wutink.dtos.UserDto;
import com.fulgay.wutink.populators.UserPopulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter extends Converter<UserDto, User> {

    @Autowired
    private UserPopulator userPopulator;

    @Override
    public User convert(UserDto source) {
        User target = new User();
        userPopulator.populate(source,target);
        return target;
    }
}
