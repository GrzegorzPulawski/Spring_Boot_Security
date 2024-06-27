package com.spring_boot_security_155.dbauth;

import java.util.Optional;

public interface UserService {
    Optional<ApplicationUser> getApplicationUserBy(String userName);
}
