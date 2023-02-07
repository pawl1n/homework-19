package com.helloworld.hello_world.service;

import com.helloworld.hello_world.repository.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> findAll();

    Student findById(Long id);

    Student createStudent(Student student);

    Student updateStudent(Student studentDetails);

    void delete(Long id);
}
