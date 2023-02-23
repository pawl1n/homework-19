package com.helloworld.hello_world.controller.dto.mapper;

import com.helloworld.hello_world.controller.dto.UserCredentialsDto;
import com.helloworld.hello_world.repository.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserCredentialsMapper {
    User toDomain(UserCredentialsDto userCredentialsDto);
}
