package com.spring_boot_security_155.dbauth;

import com.spring_boot_security_155.entity.ApplicationUserEntity;
import com.spring_boot_security_155.repository.ApplicationUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service("mysql")
public class MySqlUserService implements UserService {
    private final ApplicationUserRepository applicationUserRepository;

    public MySqlUserService(ApplicationUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    @Override
    public Optional<ApplicationUser> getApplicationUserBy(String userName) {
        return getApplicationUsers()
                .stream()
                .filter(user -> user.getUsername().equals(userName))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers() {
        return applicationUserRepository.findAll()
                .stream()
                .map(this::mapEntityToModel)
                .collect(Collectors.toList());
    }

    private ApplicationUser mapEntityToModel(ApplicationUserEntity entity) {
        return new ApplicationUser(
                entity.getPassword(),
                entity.getUsername(),
                entity.getAuthorities(),
                entity.isAccountNonExpired(),
                entity.isAccountNonLocked(),
                entity.isCredentialsNonExpired(),
                entity.isEnabled()
        );
    }

}
