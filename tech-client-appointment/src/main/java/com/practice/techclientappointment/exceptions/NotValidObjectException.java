package com.practice.techclientappointment.exceptions;

import com.practice.techclientappointment.exceptions.model.ErrorMessage;
import org.springframework.http.HttpStatus;

import java.util.Set;

public class NotValidObjectException extends BaseException {

    public NotValidObjectException() {

        super(new ErrorMessage("appointmentService", "Invalid object"));

    }

    public NotValidObjectException(Set<String> errorsList) {

        super(new ErrorMessage("appointmentService", errorsList.toString()));

    }


    public NotValidObjectException(ErrorMessage message) {
        super(message);
    }
    

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.UNPROCESSABLE_ENTITY;
    }
}
