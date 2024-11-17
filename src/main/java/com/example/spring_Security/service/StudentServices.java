package com.example.spring_Security.service;


import com.example.spring_Security.model.StudenModel.StudentModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServices {
    private List<StudentModel> students = new ArrayList<>(List.of(
            new StudentModel(
                    1L,
                    "Chanrith",
                    100
            ),
            new StudentModel(
                    2L,
                    "Socheata",
                    100
            )
    ));

    public List<StudentModel> getStudents()  {
        return students;
    }

    public StudentModel addStudent(StudentModel studentModel) {
        students.add(studentModel);
        return studentModel;
    }
}
