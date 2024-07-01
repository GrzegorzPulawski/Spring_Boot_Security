package com.spring_boot_security_155.repository;

import com.spring_boot_security_155.entity.ApplicationUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUserEntity, Long> {
}
