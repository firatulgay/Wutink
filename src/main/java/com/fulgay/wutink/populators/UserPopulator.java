package com.fulgay.wutink.populators;

import com.fulgay.wutink.domain.User;
import com.fulgay.wutink.dtos.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserPopulator implements Populator<UserDto, User> {

    @Override
    public void populate(UserDto source, User target) {
        if (source != null) {

            target.setUserName(source.getUserName());
            target.setUserType(source.getUserType());
            target.setPassword(source.getPassword());
        }
    }
}
