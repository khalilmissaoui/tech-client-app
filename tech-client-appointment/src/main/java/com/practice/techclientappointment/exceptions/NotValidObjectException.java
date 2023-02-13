package com.practice.techclientappointment.exceptions;

import org.springframework.http.HttpStatus;

public class NotValidObjectException extends BaseException {
    public NotValidObjectException() {
        super("appointmentSERVICE - Not valid object");
    }

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.UNPROCESSABLE_ENTITY;
    }
}
