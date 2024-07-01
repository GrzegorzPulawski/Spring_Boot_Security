package com.spring_boot_security_155.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Encode {
    public static void main(String[] args) {
        String password = "123456";
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        String encodedPassword = passwordEncoder.encode(password);
        System.out.println("Hasło przed kodowaniem: " + password);
        System.out.println("Hasło po zakodowaniu: " + encodedPassword);
    }
}
