package com.helloworld.hello_world.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class StudentDTO {
    private Long id;
    private String name;
    private String email;
    private List<PhotoDTO> photos;
}
