package com.helloworld.hello_world.exception;

import jakarta.persistence.EntityNotFoundException;

public class StudentNotFoundException extends EntityNotFoundException {
    private final static String MESSAGE = "Student doesn't exist";

    public StudentNotFoundException() {
        super(MESSAGE);
    }
}
