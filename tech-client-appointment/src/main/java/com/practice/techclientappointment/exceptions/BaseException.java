package com.practice.techclientappointment.exceptions;

import com.practice.techclientappointment.validations.annotations.ExceptionMessageValidation;
import org.springframework.http.HttpStatus;

public abstract class BaseException extends RuntimeException {

    public BaseException(@ExceptionMessageValidation String message) {
        super(message);
    }

    public abstract HttpStatus getStatusCode();
}

