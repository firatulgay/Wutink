package com.fulgay.bilenyum.populators;

import com.fulgay.bilenyum.domain.User;
import com.fulgay.bilenyum.dtos.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserPopulator implements Populator<UserDto, User> {

    @Override
    public void populate(UserDto source, User target) {
        if (source != null) {

            target.setUserName(source.getUserName());
            target.setUserType(source.getUserType());
            target.setName(source.getName());
            target.setPassword(source.getPassword());
            target.setSurname(source.getSurname());
        }
    }
}
