package com.helloworld.hello_world.controller.dto;

import com.helloworld.hello_world.repository.entity.Photo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PhotoMapper {
    PhotoDto toDto(Photo photo);
    List<PhotoDto> toDto(List<Photo> photos);
    Photo toDomain(PhotoDto photoDto);
}
