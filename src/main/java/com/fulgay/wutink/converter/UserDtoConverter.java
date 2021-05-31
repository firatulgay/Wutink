package com.fulgay.wutink.converter;

import com.fulgay.wutink.domain.User;
import com.fulgay.wutink.dtos.UserDto;
import com.fulgay.wutink.populators.UserDtoPopulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter extends Converter<User, UserDto> {
    @Autowired
    private UserDtoPopulator userDtoPopulator;

    @Override
    public UserDto convert(User source) {
        UserDto target = new UserDto();
        userDtoPopulator.populate(source,target);
        return target;
    }
}
