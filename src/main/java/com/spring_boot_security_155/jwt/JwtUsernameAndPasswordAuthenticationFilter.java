package com.spring_boot_security_155.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.time.LocalDate;

public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final SecretKey secretKey;
    private final JwtConfig jwtConfig;


    public JwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authenticationManager, SecretKey secretKey, JwtConfig jwtConfig) {
        this.authenticationManager = authenticationManager;
        this.secretKey = secretKey;
        this.jwtConfig = jwtConfig;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            UsernameAndPasswordAuthenticationRequest authenticationRequest = new ObjectMapper()
                    .readValue(request.getInputStream(), UsernameAndPasswordAuthenticationRequest.class);
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),
                    authenticationRequest.getPassword());
            Authentication authenticate = authenticationManager.authenticate(authentication); // prawidłowy wykonana autentykacja spowoduje wywołanie metody 'successfulAuthentication'
            return authenticate;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // jeśli
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        String token = Jwts.builder() // korzystamy z dodanej biblioteki
                .subject(authResult.getName()) //(HEADER) będą to użytkownicy, na których się logujemy
                .claim("authorities", authResult.getAuthorities()) // (BODY)
                .issuedAt(new Date()) // kiedy powstał token
                .expiration(java.sql.Date.valueOf(LocalDate.now().plusDays(jwtConfig.getTokenExpirationAfterDays())))// ustawiamy datę wygaśnięcia
                .signWith(secretKey) // podpisujemy sekretnym kluczem
                .compact();// zakańczamy budowanie Tokena
        response.addHeader(jwtConfig.getAuthorizationHeader(), jwtConfig.getTokenPrefix() + token);
    }
}
