package com.fulgay.bilenyum.populators;

import com.fulgay.bilenyum.domain.User;
import com.fulgay.bilenyum.dtos.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserDtoPopulator implements Populator<User, UserDto> {

    @Override
    public void populate(User source, UserDto target) {
        if (source != null) {
            target.setUserName(source.getUserName());
            target.setId(source.getId());
            target.setUserType(source.getUserType());
            target.setName(source.getName());
            target.setPassword(source.getPassword());
            target.setSurname(source.getSurname());
        }
    }
}
