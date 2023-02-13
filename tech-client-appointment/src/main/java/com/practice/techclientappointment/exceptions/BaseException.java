package com.practice.techclientappointment.exceptions;

import com.practice.techclientappointment.exceptions.model.ErrorMessage;
import org.springframework.http.HttpStatus;

public abstract class BaseException extends RuntimeException {

    public BaseException(ErrorMessage message) {
        super(message.getErrorMessage());
    }

    public abstract HttpStatus getStatusCode();
}

