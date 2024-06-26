package com.spring_boot_security_155.security;

public enum ApplicationUserPermission {
    STUDENT_READ("students:read"),
    STUDENT_WRITE("students:write");
    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }
    public String getPermission() {
        return permission;
    }
}
