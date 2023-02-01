package com.helloworld.hello_world.controller.dto;

import com.helloworld.hello_world.repository.entity.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentDto toDto(Student student);
    Student toDomain(StudentDto studentDto);
}
