package com.helloworld.hello_world.controller;

import com.helloworld.hello_world.controller.dto.StudentDto;
import com.helloworld.hello_world.controller.dto.StudentMapper;
import com.helloworld.hello_world.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final StudentMapper studentMapper;

    @GetMapping
    public List<StudentDto> findAll() {
        return studentService.findAll().stream().map(studentMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public StudentDto findById(@PathVariable Long id) {
        return studentMapper.toDto(studentService.findById(id));
    }

    @PostMapping
    public void createStudent(@RequestBody StudentDto studentDto) {
        studentService.createStudent(studentMapper.toDomain(studentDto));
    }

    @PutMapping
    public void updateStudent(@RequestBody StudentDto studentDto) {
        studentService.updateStudent(studentMapper.toDomain(studentDto));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        studentService.delete(id);
    }
}
