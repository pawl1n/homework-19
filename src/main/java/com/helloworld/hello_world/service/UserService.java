package com.helloworld.hello_world.service;

import com.helloworld.hello_world.repository.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User createUser(User user);

    User validateCredentials(User toDomain);
}
