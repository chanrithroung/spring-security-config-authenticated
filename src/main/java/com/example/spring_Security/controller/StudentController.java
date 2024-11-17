package com.example.spring_Security.controller;


import com.example.spring_Security.model.StudenModel.StudentModel;
import com.example.spring_Security.service.StudentServices;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    private final StudentServices studentServices;
    @Autowired
    public StudentController(StudentServices studentServices) {
        this.studentServices = studentServices;
    }

    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @GetMapping("/students")
    public List<StudentModel> getStudents() {
        return studentServices.getStudents();
    }

    @PostMapping("/students")
    public StudentModel createStudent(@RequestBody StudentModel studentModel) {
        return studentServices.addStudent(studentModel);
    }
}
