package com.spring_boot_security_155.controller;

import com.spring_boot_security_155.model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    private static final List<Student> STUDENTS = Arrays.asList(
            new Student(1, "Olivier Bond"),
            new Student(2, "Paweł Bond"),
            new Student(3, "Grzegorz Bond")
    );

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Integer id) {
        return STUDENTS.stream()
                .filter(student -> id.equals(student.getStudentId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(
                        "Student with id " + id + " not found"
                ));
    }
}
