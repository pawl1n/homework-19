package com.helloworld.hello_world.exception;

import jakarta.persistence.EntityNotFoundException;

public class PhotoNotFoundException extends EntityNotFoundException {
    private final static String MESSAGE = "Photo doesn't exist";

    public PhotoNotFoundException() {
        super(MESSAGE);
    }
}
