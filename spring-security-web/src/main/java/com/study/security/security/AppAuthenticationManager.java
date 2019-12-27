package com.study.security.security;

import com.study.security.entity.User;
import com.study.security.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AppAuthenticationManager implements AuthenticationManager {

    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if(authentication instanceof UsernamePasswordAuthenticationToken) {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken)authentication;
            User user = userService.queryByUsernameAndPassword((String)usernamePasswordAuthenticationToken.getCredentials(), (String)usernamePasswordAuthenticationToken.getCredentials());
            if(user != null) {
                usernamePasswordAuthenticationToken.setAuthenticated(true);
            } else {

            }
            return null;
        } else {
            return null;
        }
    }

    public AppAuthenticationManager(UserService userService) {
        this.userService = userService;
    }
}
