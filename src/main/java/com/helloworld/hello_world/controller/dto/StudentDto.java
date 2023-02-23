package com.helloworld.hello_world.controller.dto;

import java.util.List;

public record StudentDto(
        Long id,
        String name,
        String email,
        List<PhotoDto> photos
) {
}
