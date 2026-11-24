package com.gangu.springdatajpademo.service;

import com.gangu.springdatajpademo.model.Student;
import com.gangu.springdatajpademo.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService
{
    StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository)
    {
        this.studentRepository = studentRepository;
    }
    public void createStudent(Student student){
        studentRepository.save(student);
    }
    public Student findById(Long id){
        Optional<Student> studentOptional =
                studentRepository.findById(id);
        return studentOptional.get();
    }
    public Page<Student> fetchAll(String name){

        Sort sort = Sort.by("age").ascending().
                and(
                Sort.by("name").descending());

        Pageable pageable = PageRequest.of(0, 2,sort);
        Page<Student> studentList =
                studentRepository.findAll(pageable);
        for(Student s : studentList.getContent()){
            System.out.println(s);
        }
        return studentList;
    }
    public void updateStudent(Student studentReq , Long id){
        Optional<Student> st = studentRepository.findById(id);
        Student student = st.get();
        student.setName(studentReq.getName());
        student.setAge(studentReq.getAge());
        studentRepository.save(student);
    }
}
