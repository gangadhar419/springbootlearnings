package com.gangu.springdatajpademo.controller;

import com.gangu.springdatajpademo.model.Student;
import com.gangu.springdatajpademo.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/student")
public class StudentController
{
    private StudentService studentService;
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }
    @PostMapping
    public ResponseEntity<String> createStudent(
            @RequestBody Student student){
        studentService.createStudent(student);
        return ResponseEntity.ok().body("Done");
    }
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(
            @PathVariable Long id){
        Student student =studentService.findById(id);
        return ResponseEntity.ok().body(student);
    }
    @GetMapping("name/{name}")
    public ResponseEntity<Page<Student>> getStudent(
            @PathVariable String name){
        Page<Student> students = studentService.fetchAll(name);
        return ResponseEntity.ok(students);

    }
}
