package com.spring_boot_security_155.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests() //każde zadania musi być autoryzowane (prawa dostępu do zasobu)
                .antMatchers("/", "index.html").permitAll() // co ma być widoczne bez logowania (biała lista)
                .anyRequest() //każde żadanie
                .authenticated() // musi przejść autentykację (login i hasło)
                .and()
                .httpBasic(); // używanie podstawowej autnetykacji czyli mechanizmu BasicAuth
    }
}
