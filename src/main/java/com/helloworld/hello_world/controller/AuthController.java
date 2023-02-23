package com.helloworld.hello_world.controller;

import com.helloworld.hello_world.config.TokenProvider;
import com.helloworld.hello_world.controller.dto.UserCredentialsDto;
import com.helloworld.hello_world.controller.dto.mapper.UserCredentialsMapper;
import com.helloworld.hello_world.repository.entity.User;
import com.helloworld.hello_world.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserCredentialsMapper userCredentialsMapper;
    private final TokenProvider tokenProvider;
    private final UserService userService;


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserCredentialsDto userCredentialsDto) {
        User user = userService.createUser(userCredentialsMapper.toDomain(userCredentialsDto));

        return ResponseEntity.ok("User " + user.getEmail() + " was created!");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserCredentialsDto userCredentialsDto) {
        User user = userService.validateCredentials(userCredentialsMapper.toDomain(userCredentialsDto));
        if (user != null) {
            return ResponseEntity.ok(tokenProvider.createToken(user.getEmail()));
        }

        return ResponseEntity.status(401).body("Invalid credentials");
    }

}
