package com.practice.techclientappointment.exceptions;

import com.practice.techclientappointment.validations.annotations.ExceptionMessageValidation;
import org.springframework.http.HttpStatus;

public class NotValidObjectException extends BaseException {
    public NotValidObjectException() {
        super("appointmentSERVICE - Not valid object");
    }

    public NotValidObjectException(@ExceptionMessageValidation String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.UNPROCESSABLE_ENTITY;
    }
}
