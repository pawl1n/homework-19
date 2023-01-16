package com.helloworld.hello_world.controller;

import com.helloworld.hello_world.repository.entity.Student;
import com.helloworld.hello_world.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public List<Student> findAll() {
        return studentService.findAll();
    }

    @GetMapping("/{id}")
    public Student findById(@PathVariable Long id) {
        return studentService.findById(id);
    }

    @GetMapping("/create")
    public void createStudent(@RequestParam String name, @RequestParam String email) {
        studentService.createStudent(name, email);
    }
}
