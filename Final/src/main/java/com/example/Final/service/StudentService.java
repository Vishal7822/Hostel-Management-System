package com.example.Final.service;
import com.example.Final.model.Student;
import com.example.Final.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.getAllStudents();
    }

    public void addStudent(Student s) {
        studentRepository.addStudent(s);
    }

    public void updateStudent(Student s) {
        studentRepository.updateStudent(s);
    }

    public void deleteStudent(int id) {
        studentRepository.deleteStudent(id);
    }
}
