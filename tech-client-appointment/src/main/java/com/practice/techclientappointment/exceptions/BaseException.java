package com.practice.techclientappointment.exceptions;

import org.springframework.http.HttpStatus;

import javax.validation.constraints.NotNull;

public abstract class BaseException extends RuntimeException {

    public BaseException(@NotNull String message) {
        super(message);
    }

    public abstract HttpStatus getStatusCode();
}

