package com.practice.techclientappointment.exceptions;

import org.springframework.http.HttpStatus;

public class NotValidObjectException extends BaseException {
    public NotValidObjectException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.UNPROCESSABLE_ENTITY;
    }
}
