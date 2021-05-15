package com.fulgay.wutink.security.service.userdetails;

import com.fulgay.wutink.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserPrincipalDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        com.fulgay.wutink.domain.User userByUserName = userService.findUserByUserName(userName);
        if(userByUserName == null) {
            throw new UsernameNotFoundException("User not found!");
        }

        StringBuilder roles = new StringBuilder();
        userByUserName.getUserRoles().forEach(s -> roles.append(s));

        return User.withUsername(userByUserName.getUserName())
                .password(userByUserName.getPassword())
                .roles(roles.toString())
                .authorities(userByUserName.getUserPermissions().stream().map(userAuths -> new SimpleGrantedAuthority(userAuths)).collect(Collectors.toList()))
                .build();
    }
}
