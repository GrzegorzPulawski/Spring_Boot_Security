package com.spring_boot_security_155.jwt;

public class UsernameAndPasswordAuthenticationRequest {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
