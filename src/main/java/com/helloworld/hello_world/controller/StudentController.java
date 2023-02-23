package com.helloworld.hello_world.controller;

import com.helloworld.hello_world.controller.dto.StudentDto;
import com.helloworld.hello_world.controller.dto.mapper.StudentMapper;
import com.helloworld.hello_world.repository.entity.Student;
import com.helloworld.hello_world.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Get all students")
    @GetMapping
    public ResponseEntity<List<StudentDto>> findAll() {
        List<StudentDto> responseDto = studentMapper.toDto(studentService.findAll());

        return ResponseEntity.ok(responseDto);
    }

    @Operation(summary = "Get student by id")
    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> findById(@PathVariable Long id) {
        StudentDto responseDto = studentMapper.toDto(studentService.findById(id));

        return ResponseEntity.ok(responseDto);
    }

    @Operation(summary = "Create student")
    @PostMapping
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto) {
        Student response = studentService.createStudent(studentMapper.toDomain(studentDto));
        StudentDto responseDto = studentMapper.toDto(response);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @Operation(summary = "Update student")
    @PutMapping
    public ResponseEntity<StudentDto> updateStudent(@RequestBody StudentDto studentDto) {
        Student response = studentService.updateStudent(studentMapper.toDomain(studentDto));
        StudentDto responseDto = studentMapper.toDto(response);

        return ResponseEntity.ok(responseDto);
    }

    @Operation(summary = "Delete student by id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        studentService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
