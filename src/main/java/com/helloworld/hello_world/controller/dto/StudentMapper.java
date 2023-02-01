package com.helloworld.hello_world.controller.dto;

import com.helloworld.hello_world.repository.entity.Student;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentDto toDto(Student student);
    List<StudentDto> toDto(List<Student> students);
    Student toDomain(StudentDto studentDto);
}
