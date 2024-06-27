package com.spring_boot_security_155.security;

public enum ApplicationUserPermission {
    STUDENT_READ("students:read"),
    STUDENT_WRITE("students:write"),
    STUDENT_POST("students:post"),
    STUDENT_DELETE("students:delete"),
    STUDENT_PUT("students:put");
    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }
    public String getPermission() {
        return permission;
    }
}
