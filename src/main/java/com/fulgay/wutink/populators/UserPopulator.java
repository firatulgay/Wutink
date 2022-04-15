package com.fulgay.wutink.populators;

import com.fulgay.wutink.domain.User;
import com.fulgay.wutink.dtos.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserPopulator implements Populator<UserDto, User> {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void populate(UserDto source, User target) {
        if (source != null) {
            target.setUserName(source.getUserName());
            target.setPassword(passwordEncoder.encode(source.getPassword()));
            List<String> userRoles = source.getUserRoles();
            StringBuilder stringBuilder = new StringBuilder();
            userRoles.forEach(s -> {
                stringBuilder.append(s);
                if (userRoles.size() > 1 && !userRoles.get(userRoles.size()-1).equals(s)){
                    stringBuilder.append(",");
                }
            });
            target.setUserRoles(stringBuilder.toString());
        }
    }
}
