package com.fulgay.bilenyum.populators;

import com.fulgay.bilenyum.domain.User;
import com.fulgay.bilenyum.dtos.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserDtoPopulator implements Populator<User, UserDto> {

    private UserDto userDto;

    @Override
    public UserDto populate(User source) {
        userDto = new UserDto();

        if (source != null) {
            userDto.setUserName(source.getUserName());
            userDto.setId(source.getId());
            userDto.setUserType(source.getUserType());
            userDto.setName(source.getName());
            userDto.setPassword(source.getPassword());
            userDto.setSurname(source.getSurname());
        }
        return userDto;
    }
}
