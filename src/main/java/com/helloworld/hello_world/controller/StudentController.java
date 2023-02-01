package com.helloworld.hello_world.controller;

import com.helloworld.hello_world.controller.dto.StudentDto;
import com.helloworld.hello_world.controller.dto.StudentMapper;
import com.helloworld.hello_world.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final StudentMapper studentMapper;

    @GetMapping
    public ResponseEntity<List<StudentDto>> findAll() {
        List<StudentDto> responseDto = studentMapper.toDto(studentService.findAll());
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> findById(@PathVariable Long id) {
        StudentDto responseDto = studentMapper.toDto(studentService.findById(id));
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto) {
        StudentDto responseDto = studentMapper.toDto(studentService.createStudent(studentMapper.toDomain(studentDto)));
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<StudentDto> updateStudent(@RequestBody StudentDto studentDto) {
        StudentDto responseDto = studentMapper.toDto(studentService.updateStudent(studentMapper.toDomain(studentDto)));
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
