package com.example.Final.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Final.model.Student;
import com.example.Final.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // REMOVE this mapping entirely:
    // @GetMapping("/")
    // public String home() {

    // }

    @GetMapping("/all")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping("/add")
    public String addStudent(@RequestBody Student s) {
        studentService.addStudent(s);
        return "Student added successfully!";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
        return "Student deleted successfully!";
    }
    @PostMapping("/students/add")
    public ResponseEntity<String> addStudent1(@RequestBody Student student) {
        studentService.addStudent(student);
        return ResponseEntity.ok("Student added successfully");
    }

}
