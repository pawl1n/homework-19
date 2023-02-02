package com.helloworld.hello_world.controller.dto;

public record PhotoDto(
        Long id,
        String url,
        String description,
        Long studentId
) {
}
