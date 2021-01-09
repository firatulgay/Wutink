package com.fulgay.bilenyum.converter;

import com.fulgay.bilenyum.domain.User;
import com.fulgay.bilenyum.dtos.UserDto;
import com.fulgay.bilenyum.populators.UserDtoPopulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter extends Converter<User, UserDto> {
    @Autowired
    UserDtoPopulator userDtoPopulator;

    @Override
    public UserDto convert(User source) {
        UserDto target = new UserDto();
        userDtoPopulator.populate(source,target);
        return target;
    }
}
