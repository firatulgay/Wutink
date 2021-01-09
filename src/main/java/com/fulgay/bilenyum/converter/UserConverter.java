package com.fulgay.bilenyum.converter;

import com.fulgay.bilenyum.domain.User;
import com.fulgay.bilenyum.dtos.UserDto;
import com.fulgay.bilenyum.populators.UserPopulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter extends Converter<UserDto, User> {

    @Autowired
    UserPopulator userPopulator;

    @Override
    public User convert(UserDto source) {
        User target = new User();
        userPopulator.populate(source,target);
        return target;
    }
}
