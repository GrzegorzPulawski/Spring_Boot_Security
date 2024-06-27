package com.spring_boot_security_155.controller;

import com.spring_boot_security_155.model.Student;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/management/api/v1/students")
public class StudentManagementController {
    private static final List<Student> STUDENTS = Arrays.asList(
            new Student(1, "Olivier Bond"),
            new Student(2, "Paweł Bond"),
            new Student(3, "Grzegorz Bond")
    );

//    @PreAuthorize() przyjmuje
//    - hasRole('ROLE_ADMIN')
//    - hasAnyRole('ROLE_ADMIN', 'ROLE_STUDENT')
//    - hasAuthority('student:write')
//    - hasAnyAuthority('student:write', 'student:read')

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Student> getAllStudents() {
        return STUDENTS;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void registerNewStudent(Student student) {
        STUDENTS.add(student);
        System.out.println("registerNewStudent: " + student);
    }

}
