package com.spring_boot_security_155.controller;

import com.google.common.collect.Lists;
import com.spring_boot_security_155.model.Student;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/management/api/v1/students")
public class StudentManagementController {
    private List<Student> students = Lists.newArrayList(
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
        return students;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('students:post')")
    public String registerNewStudent(@RequestBody Student student) {
        students.add(student);
        return "registerNewStudent: " + student;
    }

    @DeleteMapping()
    @PreAuthorize("hasAuthority('students:delete')")
    public Student deleteStudent(@RequestBody Student student) {
        students.remove(student);
        return student;
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('students:put')")
    public Student updateStudent(@PathVariable int id,
                                 @RequestBody Student student) {
        return student;
    }
}
