package com.helloworld.hello_world.controller.dto;

import com.helloworld.hello_world.repository.entity.Photo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PhotoMapper {
    PhotoDTO toDTO(Photo photo);
    Photo toDomain(PhotoDTO photoDTO);
}
