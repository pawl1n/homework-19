package com.helloworld.hello_world.service;

import com.helloworld.hello_world.repository.StudentRepository;
import com.helloworld.hello_world.repository.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findById(Long id) {
        return studentRepository.findById(id).orElseThrow();
    }

    public void createStudent(String name, String email) {
        Student student = new Student();
        student.setName(name);
        student.setEmail(email);

        studentRepository.save(student);
    }

}
