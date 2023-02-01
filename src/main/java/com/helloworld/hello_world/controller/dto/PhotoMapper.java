package com.helloworld.hello_world.controller.dto;

import com.helloworld.hello_world.repository.entity.Photo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PhotoMapper {
    PhotoDto toDto(Photo photo);
    Photo toDomain(PhotoDto photoDto);
}
