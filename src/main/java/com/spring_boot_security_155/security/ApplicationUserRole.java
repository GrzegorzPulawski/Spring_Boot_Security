package com.spring_boot_security_155.security;


import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.spring_boot_security_155.security.ApplicationUserPermission.*;


public enum ApplicationUserRole {
    STUDENT(Sets.newHashSet()),
    OLD_STUDENT(Sets.newHashSet(STUDENT_WRITE)),
    HTTP_VERBS(Sets.newHashSet(STUDENT_POST, STUDENT_DELETE, STUDENT_PUT)),
    ADMIN(Sets.newHashSet(STUDENT_READ, STUDENT_WRITE, STUDENT_POST, STUDENT_DELETE, STUDENT_PUT));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permision -> new SimpleGrantedAuthority(permision.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
