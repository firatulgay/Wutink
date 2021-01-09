package com.fulgay.bilenyum.populators;

import com.fulgay.bilenyum.domain.User;
import com.fulgay.bilenyum.dtos.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserPopulator implements Populator<UserDto,User> {


    private User user;

    @Override
    public User populate(UserDto source) {
        user = new User();

        if (source != null) {

            user.setUserName(source.getUserName());
            user.setUserType(source.getUserType());
            user.setName(source.getName());
            user.setPassword(source.getPassword());
            user.setSurname(source.getSurname());
        }

        return user;
    }
}
