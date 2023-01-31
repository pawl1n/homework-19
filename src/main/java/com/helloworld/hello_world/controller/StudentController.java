package com.helloworld.hello_world.controller;

import com.helloworld.hello_world.controller.dto.StudentDTO;
import com.helloworld.hello_world.controller.dto.StudentMapper;
import com.helloworld.hello_world.repository.entity.Student;
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
    public List<StudentDTO> findAll() {
        return studentService.findAll().stream().map(studentMapper::toDTO).toList();
    }

    @GetMapping("/{id}")
    public StudentDTO findById(@PathVariable Long id) {
        return studentMapper.toDTO(studentService.findById(id));
    }

    @PostMapping
    public void createStudent(@RequestBody StudentDTO studentDTO) {
        studentService.createStudent(studentMapper.toDomain(studentDTO));
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody StudentDTO studentDTO) {
        studentService.updateStudent(id, studentMapper.toDomain(studentDTO));
        return studentMapper.toDomain(studentDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        studentService.delete(id);
    }
}
