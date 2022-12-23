package com.helloworld.hello_world.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class HelloWorldControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    void shouldReturnHelloWorld() throws Exception {
        mvc.perform(get("/hello-world"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, World!"));
    }
}