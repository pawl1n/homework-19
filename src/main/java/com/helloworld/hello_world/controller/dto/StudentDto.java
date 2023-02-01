package com.helloworld.hello_world.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class StudentDto {
    private Long id;
    private String name;
    private String email;
    private List<PhotoDto> photos;
}
