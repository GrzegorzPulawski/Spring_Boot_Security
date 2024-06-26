package com.spring_boot_security_155.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.spring_boot_security_155.security.ApplicationUserRole.*;

@Configuration
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests() //każde zadania musi być autoryzowane (prawa dostępu do zasobu)
                .antMatchers("/", "index.html").permitAll() // co ma być widoczne bez logowania (biała lista)
                .antMatchers("/api/**").hasRole(STUDENT.name())
                .anyRequest() //każde żadanie
                .authenticated() // musi przejść autentykację (login i hasło)
                .and()
                .httpBasic(); // używanie podstawowej autnetykacji czyli mechanizmu BasicAuth
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails student = User.builder()
                .username("student")
                .password(passwordEncoder.encode( "123456"))
                .roles(STUDENT.name())
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode( "123456"))
                .roles(ADMIN.name())
                .build();
        return new InMemoryUserDetailsManager(admin, student);
    }
}
