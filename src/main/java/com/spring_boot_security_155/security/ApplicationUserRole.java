package com.spring_boot_security_155.security;


import com.google.common.collect.Sets;

import java.util.Set;

import static com.spring_boot_security_155.security.ApplicationUserPermission.*;


public enum ApplicationUserRole {
    STUDENT(Sets.newHashSet()),
    OLD_STUDENT(Sets.newHashSet(STUDENT_WRITE)),
    ADMIN(Sets.newHashSet(STUDENT_READ, STUDENT_WRITE));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }
}
