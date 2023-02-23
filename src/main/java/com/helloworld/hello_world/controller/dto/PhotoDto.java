package com.helloworld.hello_world.controller.dto;

import lombok.Data;

@Data
public class PhotoDto {
    private Long id;
    private String url;
    private String description;
    private Long studentId;
}
