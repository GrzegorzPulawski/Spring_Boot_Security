package com.spring_boot_security_155.dbauth;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService implements UserDetailsService {

    private final UserService userService;

    public ApplicationUserService(@Qualifier("fake") UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.getApplicationUserBy(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(String.format("Username %s not found",username)));
    }
}
