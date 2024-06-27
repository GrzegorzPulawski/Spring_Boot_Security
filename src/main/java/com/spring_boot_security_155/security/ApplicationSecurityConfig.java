package com.spring_boot_security_155.security;

import com.spring_boot_security_155.dbauth.ApplicationUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.concurrent.TimeUnit;

import static com.spring_boot_security_155.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserService applicationUserService;

    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder, ApplicationUserService applicationUserService) {
        this.passwordEncoder = passwordEncoder;
        this.applicationUserService = applicationUserService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests() //każde zadania musi być autoryzowane (prawa dostępu do zasobu)
                .antMatchers("/", "index.html").permitAll() // co ma być widoczne bez logowania (biała lista)
                .antMatchers("/api/**").hasRole(STUDENT.name())
                .anyRequest() //każde żadanie
                .authenticated() // musi przejść autentykację (login i hasło)
                .and()
//                .httpBasic(); // używanie podstawowej autnetykacji czyli mechanizmu BasicAuth
                .formLogin()
                    .loginPage("/login")
                    .passwordParameter("password2")
                    .usernameParameter("username2")
                    .defaultSuccessUrl("/management/api/v1/students", true)
                    .permitAll()
                .and()
                .rememberMe()
                    .tokenValiditySeconds((int)TimeUnit.DAYS.toSeconds(21))
                    .rememberMeParameter("pamietaj-mnie")
                    .key("jakis_strasznie_trudny_klucz")
                .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID", "remember-me")
                    .logoutSuccessUrl("/login")
        ;
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(applicationUserService);
        return provider;
    }
//    @Override
//    @Bean
//    protected UserDetailsService userDetailsService() {
//        UserDetails student = User.builder()
//                .username("student")
//                .password(passwordEncoder.encode( "123456"))
//                .roles(STUDENT.name())
//                .authorities(STUDENT.getGrantedAuthorities())
//                .build();
//        UserDetails old = User.builder()
//                .username("old")
//                .password(passwordEncoder.encode( "123456"))
////                .roles(OLD_STUDENT.name())
//                .authorities(OLD_STUDENT.getGrantedAuthorities())
//                .build();
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder.encode( "123456"))
////                .roles(ADMIN.name())
//                .authorities(ADMIN.getGrantedAuthorities())
//                .build();
//        UserDetails verb = User.builder()
//                .username("verb")
//                .password(passwordEncoder.encode( "123456"))
////                .roles(ADMIN.name())
//                .authorities(HTTP_VERBS.getGrantedAuthorities())
//                .build();
//        return new InMemoryUserDetailsManager(admin, student, old, verb);
//    }
}
