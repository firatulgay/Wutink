package com.fulgay.wutink.populators;

import com.fulgay.wutink.domain.User;
import com.fulgay.wutink.dtos.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserDtoPopulator implements Populator<User, UserDto> {

    @Override
    public void populate(User source, UserDto target) {
        if (source != null) {
            target.setUserName(source.getUserName());
            target.setId(source.getId());
            target.setUserRoles(source.getUserRoles());
        }
    }
}
